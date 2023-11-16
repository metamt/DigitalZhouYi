package run.zhinan.zhouyi.classic.fate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import run.zhinan.zhouyi.classic.common.GanZhi;
import run.zhinan.zhouyi.common.WuXing;
import run.zhinan.zhouyi.common.YinYang;

/**
 * @author withwind
 * 纳音
 */
@Getter
@AllArgsConstructor
public class FateSound {
    public final static FateSound SOUND_01 = new FateSound( 1, "海中金", YinYang.YANG, WuXing.METAL);
    public final static FateSound SOUND_02 = new FateSound( 2, "炉中火", YinYang.YANG, WuXing.FIRE );
    public final static FateSound SOUND_03 = new FateSound( 3, "大林木", YinYang.YANG, WuXing.WOOD );
    public final static FateSound SOUND_04 = new FateSound( 4, "路旁土", YinYang.YANG, WuXing.EARTH);
    public final static FateSound SOUND_05 = new FateSound( 5, "剑峰金", YinYang.YANG, WuXing.METAL);
    public final static FateSound SOUND_06 = new FateSound( 6, "山头火", YinYang.YANG, WuXing.FIRE );
    public final static FateSound SOUND_07 = new FateSound( 7, "涧下水", YinYang.YANG, WuXing.WATER);
    public final static FateSound SOUND_08 = new FateSound( 8, "城头土", YinYang.YANG, WuXing.EARTH);
    public final static FateSound SOUND_09 = new FateSound( 9, "白腊金", YinYang.YANG, WuXing.METAL);
    public final static FateSound SOUND_10 = new FateSound(10, "杨柳木", YinYang.YANG, WuXing.WOOD );
    public final static FateSound SOUND_11 = new FateSound(11, "泉中水", YinYang.YANG, WuXing.WATER);
    public final static FateSound SOUND_12 = new FateSound(12, "屋上土", YinYang.YANG, WuXing.EARTH);
    public final static FateSound SOUND_13 = new FateSound(13, "霹雳火", YinYang.YANG, WuXing.FIRE );
    public final static FateSound SOUND_14 = new FateSound(14, "松柏木", YinYang.YANG, WuXing.WOOD );
    public final static FateSound SOUND_15 = new FateSound(15, "长流水", YinYang.YANG, WuXing.WATER);
    public final static FateSound SOUND_16 = new FateSound(16, "砂石金", YinYang.YANG, WuXing.METAL);
    public final static FateSound SOUND_17 = new FateSound(17, "山下火", YinYang.YANG, WuXing.FIRE );
    public final static FateSound SOUND_18 = new FateSound(18, "平地木", YinYang.YANG, WuXing.WOOD );
    public final static FateSound SOUND_19 = new FateSound(19, "壁上土", YinYang.YANG, WuXing.EARTH);
    public final static FateSound SOUND_20 = new FateSound(20, "金薄金", YinYang.YANG, WuXing.METAL);
    public final static FateSound SOUND_21 = new FateSound(21, "覆灯火", YinYang.YANG, WuXing.FIRE );
    public final static FateSound SOUND_22 = new FateSound(22, "天河水", YinYang.YANG, WuXing.WATER);
    public final static FateSound SOUND_23 = new FateSound(23, "大驿土", YinYang.YANG, WuXing.EARTH);
    public final static FateSound SOUND_24 = new FateSound(24, "钗环金", YinYang.YANG, WuXing.METAL);
    public final static FateSound SOUND_25 = new FateSound(25, "桑拓木", YinYang.YANG, WuXing.WOOD );
    public final static FateSound SOUND_26 = new FateSound(26, "大溪水", YinYang.YANG, WuXing.WATER);
    public final static FateSound SOUND_27 = new FateSound(27, "沙中土", YinYang.YANG, WuXing.EARTH);
    public final static FateSound SOUND_28 = new FateSound(28, "天上火", YinYang.YANG, WuXing.FIRE );
    public final static FateSound SOUND_29 = new FateSound(29, "石榴木", YinYang.YANG, WuXing.WOOD );
    public final static FateSound SOUND_30 = new FateSound(30, "大海水", YinYang.YANG, WuXing.WATER);

    public static FateSound[] values() {
        return new FateSound[] {
                SOUND_01, SOUND_02, SOUND_03, SOUND_04, SOUND_05,
                SOUND_06, SOUND_07, SOUND_08, SOUND_09, SOUND_10,
                SOUND_11, SOUND_12, SOUND_13, SOUND_14, SOUND_15,
                SOUND_16, SOUND_17, SOUND_18, SOUND_19, SOUND_20,
                SOUND_21, SOUND_22, SOUND_23, SOUND_24, SOUND_25,
                SOUND_26, SOUND_27, SOUND_28, SOUND_29, SOUND_30,
        };
    }

    int value;
    String name;
    YinYang yinYang;
    WuXing wuXing;

    public static FateSound of(GanZhi ganZhi) {
        return getByValue((ganZhi.getValue() - 1) / 2 + 1);
    }

    public static FateSound getByValue(int value) {
        return values()[value - 1];
    }

    @Override
    public String toString() {
        return getName();
    }
}
