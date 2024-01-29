package run.zhinan.zhouyi.classic.divine.liuyao;

import run.zhinan.zhouyi.classic.divine.common.HexagramDescriber;

public class LiuYaoDescriber {
    public static String describe(LiuYaoHexagram hexagram) {
        return HexagramDescriber.describe(hexagram.getHexagram()) +
                (hexagram.isConflict() ? "这是个六冲卦，六冲主凶，主快，主散。" : "") +
                (hexagram.isHarmony () ? "这是个六合卦，六合主吉，主慢，主合。" : "") + "\n";
    }

    public static String describe(LiuYaoDivine divination) {
        return  "客户提了一个占卜请求，问题是：" + divination.getQuestion() + "，占卜结果是：\n\n" +
                "原卦代表事情的起始状态，也就是现在的状态，\n" + describe(divination.origin)  +
                "变卦代表事情的最终状态，也就是结果的状态，\n" + describe(divination.result) + "\n";
    }

    public static String describe(HexagramYao yao) {
        return yao.getPosition().getName() + yao.getRelation().getName() + yao.getZhi().getFullName();
    }

    public static String describe(HexagramYaoStatus status) {
        StringBuilder result = new StringBuilder(describe(status.yao())).append("：");
        result.append("状态为").append(status.getLevel().getName()).append("(").append(status.score).append("\t)，");
        for (EffectStatus effectStatus : status.getEffects()) {
            result.append(effectStatus.effect.equals(Effect.Repeated) || effectStatus.effect.equals(Effect.Reverse) ? "与" : "被")
                  .append(effectStatus.getDescription()).append("，");
        }
        result.append("\n");
        return result.toString();
    }

    public static String describe(LiuYaoDivineStatus status) {
        StringBuilder result =  new StringBuilder(describe(status.divine) +
                "月建为：" + status.mStatus.yao.getZhi().getFullName() + "，状态为：" + status.mStatus.getLevel().getName() + "，" +
                "日建为：" + status.dStatus.yao.getZhi().getFullName() + "，状态为：" + status.dStatus.getLevel().getName() + "，" +
                "空亡为：" + status.emptyStatuses.get(0).yao.getZhi().getFullName() + " 和 " + status.emptyStatuses.get(1).yao.getZhi().getFullName() + "，" +
                "\n\n");
        LiuYaoHexagram hexagram = status.divine.origin;
        result.append("代表自己的世爻为：").append(describe(hexagram.getSelfYao())).append("，")
              .append("代表对方的应爻为：").append(describe(hexagram.getYingYao())).append("。\n\n");
        for (HexagramYaoStatus yaoStatus : status.originStatuses) {
            result.append(describe(yaoStatus));
        }
        return result.toString();
    }
}
