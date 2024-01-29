package run.zhinan.zhouyi.classic.divine.meihua;

import run.zhinan.zhouyi.classic.divine.common.HexagramDescriber;

public class MeiHuaDescriber {
    public static String describe(MeiHuaHexagram hexagram) {
        return  HexagramDescriber.describe(hexagram.getHexagram()) +
                "代表自己的体卦是" + hexagram.getSelf().getName() + "卦，" +
                "当前状态是" + hexagram.getSelfStatus().getName() + "，" +
                "这说明当前体卦处于" + hexagram.getSelfStatus().getPower() + "分的状态，这是一个" +
                (hexagram.getSelfStatus().isGood() ? "很好" : "不怎么好") + "的状态。\n" +
                "代表对方的用卦是" + hexagram.getChange().getName() + "，" +
                "当前状态是" + hexagram.getChangeStatus().getName() + "，" +
                "这说明当前用卦对体卦有" + hexagram.getChangeStatus().getPower() + "分的影响，这是一个" +
                (hexagram.getChangeStatus().isGood() ? "很强" : "比较微弱") + "的影响。\n" +
                "体用关系是用卦" + hexagram.getEffect().getName() + "体卦" + "，" +
                "吉凶状态是" + hexagram.getGoodLevel().getName() + "。\n\n";
    }

    public static String describe(MeiHuaDivine divination) {
        return  "原卦代表事情的起始状态，也就是现在的状态，" + describe(divination.origin) +
                "互卦代表事情的中间状态，也就是发展的状态，" + describe(divination.process) +
                "变卦代表事情的最终状态，也就是结果的状态，" + describe(divination.result);
    }
}
