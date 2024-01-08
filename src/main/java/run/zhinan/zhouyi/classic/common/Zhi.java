package run.zhinan.zhouyi.classic.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import run.zhinan.zhouyi.common.WuXing;
import run.zhinan.zhouyi.common.YinYang;

@Getter
@AllArgsConstructor
public class Zhi {
    public final static Zhi ZI   = new Zhi( 1, "子", YinYang.YANG, WuXing.WATER, new Gan[] {Gan.GUI });
    public final static Zhi CHOU = new Zhi( 2, "丑", YinYang.YIN , WuXing.EARTH, new Gan[] {Gan.JI  , Gan.GUI , Gan.XIN });
    public final static Zhi YIN  = new Zhi( 3, "寅", YinYang.YANG, WuXing.WOOD , new Gan[] {Gan.JIA , Gan.BING, Gan.WU  });
    public final static Zhi MAO  = new Zhi( 4, "卯", YinYang.YIN , WuXing.WOOD , new Gan[] {Gan.YI  });
    public final static Zhi CHEN = new Zhi( 5, "辰", YinYang.YANG, WuXing.EARTH, new Gan[] {Gan.WU  , Gan.YI  , Gan.GUI });
    public final static Zhi SI   = new Zhi( 6, "巳", YinYang.YIN , WuXing.FIRE , new Gan[] {Gan.BING, Gan.WU  , Gan.GENG});
    public final static Zhi WU   = new Zhi( 7, "午", YinYang.YANG, WuXing.FIRE , new Gan[] {Gan.DING, Gan.JI  });
    public final static Zhi WEI  = new Zhi( 8, "未", YinYang.YIN , WuXing.EARTH, new Gan[] {Gan.JI  , Gan.DING, Gan.YI  });
    public final static Zhi SHEN = new Zhi( 9, "申", YinYang.YANG, WuXing.METAL, new Gan[] {Gan.GENG, Gan.REN , Gan.WU  });
    public final static Zhi YOU  = new Zhi(10, "酉", YinYang.YIN , WuXing.METAL, new Gan[] {Gan.XIN });
    public final static Zhi XU   = new Zhi(11, "戌", YinYang.YANG, WuXing.EARTH, new Gan[] {Gan.WU  , Gan.XIN , Gan.DING});
    public final static Zhi HAI  = new Zhi(12, "亥", YinYang.YIN , WuXing.WATER, new Gan[] {Gan.REN , Gan.JIA });

    public static Zhi[] values() {
        return new Zhi[] {ZI, CHOU, YIN, MAO, CHEN, SI, WU, WEI, SHEN, YOU, XU, HAI};
    }

    int     value;
    String  name;
    YinYang yinYang;
    WuXing  wuXing;
    Gan[]   hiddenGans;

    public static Zhi getByValue(int value) {
        return values()[value - 1];
    }

    public static Zhi getByName(String name) {
        Zhi result = null;
        for (Zhi z : values()) {
            if (z.name.equals(name)) {
                result = z;
                break;
            }
        }
        return result;
    }

    public Gan getGan() {
        return firstHiddenGan();
    }

    public Gan firstHiddenGan() {
        return getHiddenGans()[0];
    }

    public Gan secondHiddenGan() {
        return getHiddenGans().length > 1 ? hiddenGans[1] : null;
    }

    public Gan thirdHiddenGan() {
        return getHiddenGans().length > 2 ? hiddenGans[2] : null;
    }

    public Gan[] hiddenGans() {return hiddenGans;}

    Gan[] getHiddenGans() {return hiddenGans();}

    @Override
    public String toString() {
        return getName();
    }
}
