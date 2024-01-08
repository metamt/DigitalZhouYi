package run.zhinan.zhouyi.classic.divine.liuyao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import run.zhinan.zhouyi.classic.common.EffectType;
import run.zhinan.zhouyi.classic.common.Zhi;
import run.zhinan.zhouyi.classic.common.ZhiEffect;
import run.zhinan.zhouyi.common.WuXingEffect;

@Getter
@AllArgsConstructor
public enum Effect {
    Conflict("冲", -80),
    Combine ("合",  80),
    Same    ("临", 100),
    Give    ("生",  60),
    Help    ("帮",  50),
    Leak    ("泄", -20),
    Cost    ("耗", -30),
    Curb    ("克", -60);

    String name;
    int    power;

    public int getValue() {
        return ordinal();
    }

    public static Effect of(Zhi self, Zhi other) {
        if (ZhiEffect.is6Conflict(self, other)) return Conflict;
        if (ZhiEffect.is6Combine (self, other)) return Combine ;
        if (self.equals(other)) return Same;
        WuXingEffect effect = WuXingEffect.of(self.getWuXing(), other.getWuXing());
        return values()[effect.getValue() + Give.getValue()];
    }
}
