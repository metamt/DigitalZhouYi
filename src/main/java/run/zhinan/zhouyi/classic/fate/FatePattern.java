package run.zhinan.zhouyi.classic.fate;

import run.zhinan.zhouyi.common.WuXing;

import java.util.List;

public class FatePattern {
    int value;
    String name;

    int selfPart;
    int otherPart;

    boolean strong;
    boolean follow;

    List<WuXing> goodGodList;
    List<WuXing>  badGodList;

    public static FatePattern of(FateCode bazi) {
//        FatePattern pattern = new FatePattern();
//        pattern.value       = model.getValue();
//        pattern.name        = model.getName();
//        pattern.selfPart    = model.getSelfPart ();
//        pattern.otherPart   = model.getOtherPart();
//        pattern.follow      = model.isFollow();
//        pattern.strong      = model.isStrong();
//        pattern.goodGodList = model.getGoodGodList();
//        pattern. badGodList = model.getBadGodList();
        return new FatePattern();
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

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public int getSelfPart() {
        return selfPart;
    }

    public int getOtherPart() {
        return otherPart;
    }

    public boolean isStrong() {
        return strong;
    }

    public boolean isFollow() {
        return follow;
    }

    public List<WuXing> getGoodGodList() {
        return goodGodList;
    }

    public List<WuXing> getBadGodList() {
        return badGodList;
    }
}
