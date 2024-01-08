package run.zhinan.zhouyi.classic.divine.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import run.zhinan.zhouyi.common.WuXing;
import run.zhinan.zhouyi.common.YinYang;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@AllArgsConstructor
public enum BasicHexagram {
    QIAN("111", "乾", "天", 1, 6, WuXing.METAL, YinYang.YANG),
    DUI ("110", "兑", "泽", 2, 8, WuXing.METAL, YinYang.YIN ),
    LI  ("101", "离", "火", 3, 9, WuXing.FIRE , YinYang.YIN ),
    ZHEN("100", "震", "雷", 4, 3, WuXing.WOOD , YinYang.YANG),
    XUN ("011", "巽", "风", 5, 4, WuXing.WOOD , YinYang.YIN ),
    KAN ("010", "坎", "水", 6, 1, WuXing.WATER, YinYang.YANG),
    GEN ("001", "艮", "山", 7, 7, WuXing.EARTH, YinYang.YANG),
    KUN ("000", "坤", "地", 8, 2, WuXing.EARTH, YinYang.YIN );

    String code;
    String name;
    String symbol;
    int    initValue;
    int    alterValue;

    WuXing  wuXing;
    YinYang yinYang;

    public final static BasicHexagram[]  initValues = new BasicHexagram[] {QIAN, DUI, LI, ZHEN, XUN, KAN, GEN, KUN};
    public final static BasicHexagram[] alterValues = new BasicHexagram[] {KAN, KUN, ZHEN, XUN, KUN, QIAN, DUI, GEN, LI};
    public final static Map<String, BasicHexagram> codeMap = new ConcurrentHashMap<>();

    static {
        for (BasicHexagram hexagrams : values()) {
            codeMap.put(hexagrams.getCode(), hexagrams);
        }
    }

    public static BasicHexagram getByInitValue(int value) {
        return initValues[value - 1];
    }

    public static BasicHexagram getByAlterValue(int value) {
        return alterValues[value - 1];
    }

    public static BasicHexagram getByCode(String code) {
        return codeMap.get(code);
    }
}
