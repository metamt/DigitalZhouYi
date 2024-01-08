package run.zhinan.zhouyi.classic.divine.liuyao;

import lombok.Getter;
import run.zhinan.zhouyi.classic.common.GanZhi;
import run.zhinan.zhouyi.classic.common.Zhi;

import java.util.*;

public class LiuYaoDivineStatus {
    LiuYaoDivine divine;

    HexagramYaoStatus mStatus;
    HexagramYaoStatus dStatus;

    List<HexagramYaoStatus> originStatuses = new ArrayList<>();
    List<HexagramYaoStatus> resultStatuses = new ArrayList<>();

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
    }

    public static LiuYaoDivineStatus of(LiuYaoDivine divine) {
        LiuYaoDivineStatus divineStatus = new LiuYaoDivineStatus(divine);

        // 0. 处理旬空
        divineStatus.getAllYaoStatuses().forEach(divineStatus::process);

        // 1. 月建作用，能作用月建的，只有是否旬空
        divineStatus.getAllYaoStatuses().forEach(yaoStatus -> divineStatus.process(yaoStatus, divineStatus.mStatus));
        // 2. 日建作用，能作用日建的，只有是否旬空
        divineStatus.getAllYaoStatuses().forEach(yaoStatus -> divineStatus.process(yaoStatus, divineStatus.dStatus));
        // 3. 如果是动爻，本位变爻作用
        divineStatus.getChangeStatuses().forEach(yaoStatus -> divineStatus.process(yaoStatus, Objects.requireNonNull(divineStatus.getChangeYaoStatus(yaoStatus))));
        // 4. 其他动爻作用
        List<HexagramYaoStatus> currentChangeYaoStatus = new ArrayList<>();
        divineStatus.getChangeStatuses().forEach(yaoStatus -> currentChangeYaoStatus.add(yaoStatus.copy()));
        currentChangeYaoStatus.forEach(changeStatus -> divineStatus.originStatuses.forEach(yaoStatus -> divineStatus.process(yaoStatus, changeStatus)));
        // 5. 如果是伏爻，本位静爻作用
        divineStatus.getFlyingStatuses().forEach(yaoStatus -> {
            HexagramYaoStatus hiddenYaoStatus = new HexagramYaoStatus(yaoStatus.yao.getHidden(), 20);
            divineStatus.process(hiddenYaoStatus, yaoStatus);
            divineStatus.originStatuses.add(hiddenYaoStatus);
        });

        divineStatus.originStatuses.forEach(yaoStatus -> divineStatus.relationMap.put(yaoStatus.yao.getRelation().getName(), yaoStatus.score));
        return divineStatus;
    }

    private List<HexagramYaoStatus> getEmptyStatuses () {
        List<HexagramYaoStatus> statuses = new ArrayList<>();
        for (Zhi zhi : divine.dayEmpty) {
            statuses.add(new HexagramYaoStatus(new HexagramYao(null, zhi, zhi.getYinYang(), zhi.getWuXing(), Relation.Empty, Position.ENVIRONMENT)));
        }
        return statuses;
    }

    private List<HexagramYaoStatus> getAllYaoStatuses() {
        List<HexagramYaoStatus> statuses = new ArrayList<>();
        statuses.add(mStatus);
        statuses.add(dStatus);
        statuses.addAll(originStatuses);
        statuses.addAll(resultStatuses);
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

    private void process(HexagramYaoStatus target, HexagramYaoStatus actor) {
        if (actor != null)
            target.score += Effect.of(target.getYao().getZhi(), actor.yao.getZhi()).getPower() / 100 * actor.score;
    }

    private void process(HexagramYaoStatus yaoStatus) {
        Zhi[] empty = divine.getDayEmpty();
        if (empty[0].equals(yaoStatus.yao.getZhi()) || empty[1].equals(yaoStatus.yao.getZhi())) {
            yaoStatus.score -= 80;
        }
    }

    private HexagramYaoStatus getChangeYaoStatus(HexagramYaoStatus yaoStatus) {
        return yaoStatus.yao.change ? new HexagramYaoStatus(divine.getResult().getYao(yaoStatus.yao.position.getValue())) : null;
    }
}
