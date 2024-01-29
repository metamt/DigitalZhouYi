package run.zhinan.zhouyi.classic.divine.liuyao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import run.zhinan.zhouyi.classic.common.Zhi;
import run.zhinan.zhouyi.classic.common.ZhiEffect;
import run.zhinan.zhouyi.common.WuXingEffect;

import java.util.function.BiFunction;

@Getter
@AllArgsConstructor
public enum Effect {
    Conflict("破",  -80, "天时不利", ZhiEffect::is6Conflict),
    Combine ("旺",   80, "天顺地和", ZhiEffect::is6Combine ),
    Same    ("临",  100, "天神降临", Object::equals),
    Support ("扶",   90, "如有神助", (t, u) -> !t.equals(u) && t.getWuXing().equals(u.getWuXing())),

    Give    ("生",   60, "倾力支持", (t, u) -> WuXingEffect.of(t.getWuXing(), u.getWuXing()).equals(WuXingEffect.GIVE)),
    Help    ("帮",   50, "尽力相帮", (t, u) -> WuXingEffect.of(t.getWuXing(), u.getWuXing()).equals(WuXingEffect.HELP)),
    Leak    ("泄",  -20, "一泄如注", (t, u) -> WuXingEffect.of(t.getWuXing(), u.getWuXing()).equals(WuXingEffect.LEAK)),
    Cost    ("耗",  -30, "费力消耗", (t, u) -> WuXingEffect.of(t.getWuXing(), u.getWuXing()).equals(WuXingEffect.COST)),
    Curb    ("克",  -60, "用力阻挠", (t, u) -> WuXingEffect.of(t.getWuXing(), u.getWuXing()).equals(WuXingEffect.CURB)),

    Forward ("化进", 70, "力量增强", (t, u) -> t.getWuXing().equals(u.getWuXing()) && t.getValue() <  u.getValue()),
    Backward("化退", 30, "力量减弱", (t, u) -> t.getWuXing().equals(u.getWuXing()) && t.getValue() >  u.getValue()),
    Repeated("伏吟",-20, "痛苦煎熬", (t, u) -> t.getWuXing().equals(u.getWuXing()) && t.getValue() == u.getValue()),
    Reverse ("反吟",-20, "多有反复", (t, u) -> t.getWuXing().equals(u.getWuXing()) && ZhiEffect.is6Conflict(t, u));

    final String name;
    final int    power;
    final String description;
    final BiFunction<Zhi, Zhi, Boolean> match;

    public final static Effect[] normalEffects = new Effect[] {Give, Leak, Cost, Curb, Forward, Backward, Repeated, Reverse};

    public int getValue() {
        return ordinal();
    }
}
