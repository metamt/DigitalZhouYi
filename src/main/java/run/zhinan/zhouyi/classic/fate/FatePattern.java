package run.zhinan.zhouyi.classic.fate;

import lombok.Getter;
import run.zhinan.zhouyi.classic.fate.energy.EnergyType;
import run.zhinan.zhouyi.classic.fate.energy.WuXingEnergy;
import run.zhinan.zhouyi.common.WuXing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static run.zhinan.zhouyi.classic.fate.PatternType.*;
import static run.zhinan.zhouyi.common.WuXingEffect.*;

@Getter
public class FatePattern {
    int selfPart;
    int otherPart;

    boolean strong;
    boolean follow;

    EnergyType  energyType;

    FatePatternType fatePatternType;

    List<WuXing> goodGodList = new ArrayList<>();
    List<WuXing>  badGodList = new ArrayList<>();

    /**
     * 身强条件：
     * 同党大于异党 或
     * 同党与异党相同时，看月令，
     * 月令是同党则是身强，
     * 月令如果是异党则是身弱。
     *
     * 从格条件：
     * 同党<=80为从弱
     * 异党<=40委从强
     */
    public static FatePattern of(FateCode fateCode) {
        FatePattern pattern = new FatePattern();
        WuXingEnergy energy = WuXingEnergy.of(fateCode);
        WuXing fateWuXing = fateCode.getFate().getWuXing();
        pattern.selfPart  = energy.getValue(fateWuXing.getByEffect(GIVE)) +
                            energy.getValue(fateWuXing.getByEffect(HELP)) ;
        pattern.otherPart = energy.getValue(fateWuXing.getByEffect(LEAK)) +
                            energy.getValue(fateWuXing.getByEffect(COST)) +
                            energy.getValue(fateWuXing.getByEffect(CURB)) ;


        pattern.strong     = pattern.selfPart > pattern.otherPart ||
                (pattern.selfPart == pattern.otherPart && fateWuXing.effect(fateCode.getMaster().getWuXing()).getValue() < LEAK.getValue());

        pattern.follow     = pattern.selfPart <= 80 || pattern.otherPart <= 40;

        pattern.energyType = EnergyType.of(pattern.strong, pattern.follow);
        pattern.fatePatternType = FatePatternType.of(fateWuXing.effect(energy.getMax()), pattern.follow ? FOLLOW : ADJUST);

        Arrays.asList(pattern.fatePatternType.goodEffects).forEach(effect -> pattern.goodGodList.add(fateWuXing.getByEffect(effect)));
        Arrays.asList(pattern.fatePatternType. badEffects).forEach(effect -> pattern. badGodList.add(fateWuXing.getByEffect(effect)));

        return pattern;
    }

    public boolean isGood(WuXing wuXing) {
        return getGoodGodList().contains(wuXing);
    }

    public boolean isBad(WuXing wuXing) {
        return !isGood(wuXing);
    }

    public WuXing getFirstGoodGod() {
        return getGoodGodList().size() > 0 ? getGoodGodList().get(0) : null;
    }

    public WuXing getSecondGoodGod() {
        return getGoodGodList().size() > 1 ? getGoodGodList().get(1) : null;
    }

    public WuXing getThirdGoodGod() {
        return getGoodGodList().size() > 2 ? getGoodGodList().get(2) : null;
    }

    public WuXing getFirstBadGod() {
        return getBadGodList().size() > 0 ? getBadGodList().get(0) : null;
    }

    public WuXing getSecondBadGod() {
        return getBadGodList().size() > 1 ? getBadGodList().get(1) : null;
    }

    public WuXing getThirdBadGod() {
        return getBadGodList().size() > 2 ? getBadGodList().get(2) : null;
    }
}
