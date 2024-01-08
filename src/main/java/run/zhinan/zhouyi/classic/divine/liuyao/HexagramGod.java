package run.zhinan.zhouyi.classic.divine.liuyao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import run.zhinan.zhouyi.common.WuXing;
import run.zhinan.zhouyi.common.YinYang;

@Getter
@AllArgsConstructor
public enum HexagramGod {
    GreenDragon  ("青龙", YinYang.YANG, WuXing.WOOD ),
    VermilionBird("朱雀", YinYang.YANG, WuXing.FIRE ),
    GoldKylin    ("勾陈", YinYang.YANG, WuXing.EARTH),
    FlySerpent   ("螣蛇", YinYang.YIN , WuXing.EARTH),
    WhiteTiger   ("白虎", YinYang.YANG, WuXing.METAL),
    BlackTortoise("玄武", YinYang.YANG, WuXing.WATER);

    String  name;
    YinYang yinYang;
    WuXing  wuXing;

    public int getValue() {return ordinal();}

    public static HexagramGod getByValue(int value) {
        return values()[value];
    }

}
