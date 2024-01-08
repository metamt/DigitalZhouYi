package run.zhinan.zhouyi.classic.divine.meihua;

import lombok.AllArgsConstructor;
import lombok.Getter;
import run.zhinan.zhouyi.common.WuXingEffect;

@Getter
@AllArgsConstructor
public enum GoodLevel {
    GREAT("大吉"), GOOD("吉"), OK("平"), BAD("凶"), WORST("大凶");

    private final static GoodLevel[] values = new GoodLevel[] {GREAT, GOOD, BAD, OK, WORST};

    String name;

    public static GoodLevel getByEffect(WuXingEffect effect) {
        return effect == null ? null : values[effect.getValue()];
    }
}
