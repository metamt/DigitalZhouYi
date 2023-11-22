package run.zhinan.zhouyi.classic.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import run.zhinan.zhouyi.classic.fate.FateGod;
import run.zhinan.zhouyi.common.WuXing;
import run.zhinan.zhouyi.common.YinYang;

@Getter
@AllArgsConstructor
public class Gan {
    public final static Gan JIA  = new Gan( 1, "甲", YinYang.YANG, WuXing.WOOD );
    public final static Gan YI   = new Gan( 2, "乙", YinYang.YIN , WuXing.WOOD );
    public final static Gan BING = new Gan( 3, "丙", YinYang.YANG, WuXing.FIRE );
    public final static Gan DING = new Gan( 4, "丁", YinYang.YIN , WuXing.FIRE );
    public final static Gan WU   = new Gan( 5, "戊", YinYang.YANG, WuXing.EARTH);
    public final static Gan JI   = new Gan( 6, "己", YinYang.YIN , WuXing.EARTH);
    public final static Gan GENG = new Gan( 7, "庚", YinYang.YANG, WuXing.METAL);
    public final static Gan XIN  = new Gan( 8, "辛", YinYang.YIN , WuXing.METAL);
    public final static Gan REN  = new Gan( 9, "壬", YinYang.YANG, WuXing.WATER);
    public final static Gan GUI  = new Gan(10, "癸", YinYang.YIN , WuXing.WATER);

    public static Gan[] values() {
        return new Gan[] {JIA, YI, BING, DING, WU, JI, GENG, XIN, REN, GUI};
    }

    int     value;
    String  name;
    YinYang yinYang;
    WuXing  wuXing;

    public static Gan getByValue(int value) {
        return values()[value - 1];
    }

    public static Gan getByName(String name) {
        Gan result = null;
        for (Gan g : values()) {
            if (g.name.equals(name)) {
                result = g;
                break;
            }
        }
        return result;
    }

    public static Gan of(YinYang yinYang, WuXing wuXing) {
        return values()[wuXing.getValue() * 2 + 1 - yinYang.getValue()];
    }

    public FateGod effect(Gan other) {
        return FateGod.of(this, other);
    }

    @Override
    public String toString() {
        return getName();
    }
}
