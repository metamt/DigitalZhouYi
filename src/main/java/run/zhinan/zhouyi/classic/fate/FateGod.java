package run.zhinan.zhouyi.classic.fate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import run.zhinan.zhouyi.classic.common.Gan;
import run.zhinan.zhouyi.common.WuXingEffect;
import run.zhinan.zhouyi.common.YinYang;

@Getter
@AllArgsConstructor
public class FateGod {
    public final static FateGod ZHENG_YIN    = new FateGod(0, "正印", YinYang.YANG, WuXingEffect.GIVE);
    public final static FateGod PIAN_YIN     = new FateGod(1, "偏印", YinYang.YIN , WuXingEffect.GIVE);
    public final static FateGod JIE_CAI      = new FateGod(2, "劫财", YinYang.YANG, WuXingEffect.HELP);
    public final static FateGod BI_JIAN      = new FateGod(3, "比肩", YinYang.YIN , WuXingEffect.HELP);
    public final static FateGod SHANG_GUAN   = new FateGod(4, "伤官", YinYang.YANG, WuXingEffect.LEAK);
    public final static FateGod SHI_SHEN     = new FateGod(5, "劫财", YinYang.YIN , WuXingEffect.LEAK);
    public final static FateGod ZHENG_CAI    = new FateGod(6, "正财", YinYang.YANG, WuXingEffect.COST);
    public final static FateGod PIAN_CAI     = new FateGod(7, "偏财", YinYang.YIN , WuXingEffect.COST);
    public final static FateGod ZHENG_GUAN   = new FateGod(8, "正官", YinYang.YANG, WuXingEffect.CURB);
    public final static FateGod PIAN_GUAN    = new FateGod(9, "七杀", YinYang.YIN , WuXingEffect.CURB);

    public static FateGod[] values() {
        return new FateGod[] {ZHENG_YIN, PIAN_YIN, JIE_CAI, BI_JIAN, SHANG_GUAN, SHI_SHEN, ZHENG_CAI, PIAN_CAI, ZHENG_GUAN, PIAN_GUAN};
    }

    int value;
    String name;
    YinYang yinYang;
    WuXingEffect effect;

    public static FateGod getByYinYangWuXingEffect(YinYang yinYang, WuXingEffect effect) {
        return values()[effect.getValue() * 2 + 1 - yinYang.getValue()];
    }

    public static FateGod of(Gan self, Gan other) {
        return getByYinYangWuXingEffect(
                self.getYinYang().effect(other.getYinYang()),
                self.getWuXing().effect(other.getWuXing()));
    }
}
