package run.zhinan.zhouyi.classic.divine.liuyao;

import lombok.Getter;
import run.zhinan.zhouyi.classic.common.GanZhi;
import run.zhinan.zhouyi.classic.common.Zhi;

import java.util.*;

public class LiuYaoDivineStatus {
    LiuYaoDivine divine;

    HexagramYaoStatus mStatus;
    HexagramYaoStatus dStatus;
    List<HexagramYaoStatus> emptyStatuses;
    @Getter
    List<HexagramYaoStatus> originStatuses = new ArrayList<>();
    List<HexagramYaoStatus> resultStatuses = new ArrayList<>();

    List<HexagramYaoStatus> environmentStatuses;
    List<HexagramYaoStatus> allYaoStatuses;
    List<HexagramYaoStatus> changeStatuses;
    List<HexagramYaoStatus> flyingStatuses;
    List<HexagramYaoStatus> hiddenStatuses;

    @Getter
    Map<String, Integer> relationMap = new HashMap<>();

    public LiuYaoDivineStatus(LiuYaoDivine divine) {
        this.divine  = divine;
        GanZhi mGanZhi = GanZhi.of(divine.getGanZhiDateTime().getGanZhiMonth());
        GanZhi dGanZhi = GanZhi.of(divine.getGanZhiDateTime().getGanZhiDay  ());
        this.mStatus = new HexagramYaoStatus(new HexagramYao(mGanZhi.getGan(), mGanZhi.getZhi(), mGanZhi.getZhi().getYinYang(), mGanZhi.getZhi().getWuXing(), Relation.Month, Position.ENVIRONMENT));
        this.dStatus = new HexagramYaoStatus(new HexagramYao(dGanZhi.getGan(), dGanZhi.getZhi(), dGanZhi.getZhi().getYinYang(), dGanZhi.getZhi().getWuXing(), Relation.Day  , Position.ENVIRONMENT));

        for (int i = 0; i < 6; i++) {
            this.originStatuses.add(new HexagramYaoStatus(divine.getOrigin().getYao(i)));
            this.resultStatuses.add(new HexagramYaoStatus(divine.getResult().getYao(i)));
        }

        this.emptyStatuses  = getEmptyStatuses ();
        this.environmentStatuses = getEnvironmentStatus();
        this.flyingStatuses = getFlyingStatuses();
        this.hiddenStatuses = getHiddenStatuses();
        this.changeStatuses = getChangeStatuses();
        this.allYaoStatuses = getAllYaoStatuses();
    }

    public static LiuYaoDivineStatus of(LiuYaoDivine divine) {
        LiuYaoDivineStatus divineStatus = new LiuYaoDivineStatus(divine);

        // 0. 处理旬空
        divineStatus.environmentStatuses.forEach(divineStatus::process);
        divineStatus.allYaoStatuses     .forEach(divineStatus::process);
        // 1. 月建作用，能作用月建的，只有是否旬空
        divineStatus.allYaoStatuses.forEach(yaoStatus -> divineStatus.process(yaoStatus, divineStatus.mStatus));
        // 2. 日建作用，能作用日建的，只有是否旬空
        divineStatus.allYaoStatuses.forEach(yaoStatus -> divineStatus.process(yaoStatus, divineStatus.dStatus));
        // 3. 如果是动爻，本位变爻作用
        divineStatus.changeStatuses.forEach(yaoStatus -> divineStatus.process(yaoStatus, Objects.requireNonNull(divineStatus.getChangeYaoStatus(yaoStatus))));
        // 4. 其他动爻作用
        List<HexagramYaoStatus> currentChangeYaoStatus = new ArrayList<>();
        divineStatus.changeStatuses.forEach(yaoStatus -> currentChangeYaoStatus.add(yaoStatus.copy()));
        currentChangeYaoStatus.forEach(changeStatus -> divineStatus.originStatuses.forEach(yaoStatus -> divineStatus.process(yaoStatus, changeStatus)));
        // 5. 如果是伏爻，本位静爻作用
        for (int i = 0; i < divineStatus.flyingStatuses.size(); i++) {
            HexagramYaoStatus hiddenYaoStatus = divineStatus.hiddenStatuses.get(i);
            divineStatus.process(hiddenYaoStatus, divineStatus.flyingStatuses.get(i));
            divineStatus.originStatuses.add(hiddenYaoStatus);
        }

        divineStatus.originStatuses.forEach(yaoStatus -> divineStatus.relationMap.put(yaoStatus.yao.getRelation().getName(), yaoStatus.score));
        return divineStatus;
    }

    private List<HexagramYaoStatus> getEmptyStatuses () {
        List<HexagramYaoStatus> statuses = new ArrayList<>();
        for (Zhi zhi : divine.dayEmpty) {
            statuses.add(new HexagramYaoStatus(new HexagramYao(null, zhi, zhi.getYinYang(), zhi.getWuXing(), Relation.Empty, Position.ENVIRONMENT), 80));
        }
        return statuses;
    }

    private List<HexagramYaoStatus> getEnvironmentStatus() {
        List<HexagramYaoStatus> statuses = new ArrayList<>();
        statuses.add(mStatus);
        statuses.add(dStatus);
        return statuses;
    }

    private List<HexagramYaoStatus> getAllYaoStatuses() {
        List<HexagramYaoStatus> statuses = new ArrayList<>();
        statuses.addAll(originStatuses);
        statuses.addAll(resultStatuses);
        statuses.addAll(hiddenStatuses);
        return statuses;
    }

    private List<HexagramYaoStatus> getChangeStatuses() {
        List<HexagramYaoStatus> statuses = new ArrayList<>();
        for (HexagramYaoStatus status : originStatuses) {
            if (status.yao.change) statuses.add(status);
        }
        return statuses;
    }

    private List<HexagramYaoStatus> getFlyingStatuses() {
        List<HexagramYaoStatus> statuses = new ArrayList<>();
        for (HexagramYaoStatus status : originStatuses) {
            if (status.yao.fly) statuses.add(status);
        }
        return statuses;
    }

    private List<HexagramYaoStatus> getHiddenStatuses() {
        List<HexagramYaoStatus> result = new ArrayList<>();
        for (HexagramYaoStatus flying : flyingStatuses) {
            result.add(new HexagramYaoStatus(flying.yao.getHidden(), 20));
        }
        return result;
    }

    private HexagramYaoStatus getChangeYaoStatus(HexagramYaoStatus yaoStatus) {
        return yaoStatus.yao.change ? new HexagramYaoStatus(divine.getResult().getYao(yaoStatus.yao.position.getValue())) : null;
    }

    private void process(HexagramYaoStatus target, HexagramYaoStatus actor) {
        if (actor != null) {
            for (Effect effect : actor.yao().getRelation().effects) {
                if (effect.match.apply(target.yao().getZhi(), actor.yao().getZhi())) {
                    int power = effect.getPower() * Math.max(actor.score, 0) / 100;
                    target.score += power;
                    EffectStatus effectStatus = new EffectStatus(target.yao(), actor.yao(), effect, power);
                    target.getEffects().add(effectStatus);
                    target.beConflicted     = target.beConflicted     || effectStatus.beConflicted();
                    target.beControlled     = target.beControlled     || effectStatus.beControlled();
                    target.beBackGenerated  = target.beBackGenerated  || effectStatus.beBackGenerated();
                    target.beBackControlled = target.beBackControlled || effectStatus.beBackControlled();
                    break;
                }
            }
        }
    }

    private void process(HexagramYaoStatus yaoStatus) {
        Zhi[] empty = divine.getDayEmpty();
        if (empty[0].equals(yaoStatus.yao.getZhi()) || empty[1].equals(yaoStatus.yao.getZhi())) {
            yaoStatus.score -= 80;
        }
    }
}
