package run.zhinan.zhouyi.classic.divine.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import run.zhinan.zhouyi.common.YinYang;

@Getter
@AllArgsConstructor
public enum CompositeHexagram {
    H11( 1, "乾为天",   1, 1, BasicHexagram.QIAN, 0),
    H88( 2, "坤为地",   8, 8, BasicHexagram.KUN , 0),
    H64( 3, "水雷屯",   6, 4, BasicHexagram.KAN , 2),
    H76( 4, "山水蒙",   7, 6, BasicHexagram.LI  , 4),
    H61( 5, "水天需",   6, 1, BasicHexagram.KUN , 6),
    H16( 6, "天水讼",   1, 6, BasicHexagram.LI  , 6),
    H86( 7, "地水师",   8, 6, BasicHexagram.KAN , 7),
    H68( 8, "水地比",   6, 8, BasicHexagram.KUN , 7),
    H51( 9, "风天小畜", 5, 1, BasicHexagram.XUN , 1),
    H12(10, "天泽履",   1, 2, BasicHexagram.GEN , 5),
    H81(11, "地天泰",   8, 1, BasicHexagram.KUN , 3),
    H18(12, "天地否",   1, 8, BasicHexagram.QIAN, 3),
    H13(13, "天火同人", 1, 3, BasicHexagram.LI  , 7),
    H31(14, "火天大有", 3, 1, BasicHexagram.QIAN, 7),
    H87(15, "地山谦",   8, 7, BasicHexagram.DUI , 5),
    H48(16, "雷地豫",   4, 8, BasicHexagram.ZHEN, 1),
    H24(17, "泽雷随",   2, 4, BasicHexagram.ZHEN, 7),
    H75(18, "山风蛊",   7, 5, BasicHexagram.XUN , 7),
    H82(19, "地泽临",   8, 2, BasicHexagram.KUN , 2),
    H58(20, "风地观",   5, 8, BasicHexagram.QIAN, 4),
    H34(21, "火雷噬嗑", 3, 4, BasicHexagram.XUN , 5),
    H73(22, "山火贲",   7, 3, BasicHexagram.GEN , 1),
    H78(23, "山地剥",   7, 8, BasicHexagram.QIAN, 5),
    H84(24, "地雷复",   8, 4, BasicHexagram.KUN , 1),
    H14(25, "天雷无妄", 1, 4, BasicHexagram.XUN , 4),
    H71(26, "山天大畜", 7, 1, BasicHexagram.GEN , 2),
    H74(27, "山雷颐",   7, 4, BasicHexagram.XUN , 6),
    H25(28, "泽风大过", 2, 5, BasicHexagram.ZHEN, 6),
    H66(29, "坎为水",   6, 6, BasicHexagram.KAN , 0),
    H33(30, "离为火",   3, 3, BasicHexagram.LI  , 0),
    H27(31, "泽山咸",   2, 7, BasicHexagram.DUI , 3),
    H45(32, "雷风恒",   4, 5, BasicHexagram.ZHEN, 3),
    H17(33, "天山遁",   1, 7, BasicHexagram.QIAN, 2),
    H41(34, "雷天大壮", 4, 1, BasicHexagram.KUN , 4),
    H38(35, "火地晋",   3, 8, BasicHexagram.QIAN, 6),
    H83(36, "地火明夷", 8, 3, BasicHexagram.KAN , 6),
    H53(37, "风火家人", 5, 3, BasicHexagram.XUN , 2),
    H32(38, "火泽睽",   3, 2, BasicHexagram.GEN , 4),
    H67(39, "水山蹇",   6, 7, BasicHexagram.DUI , 4),
    H46(40, "雷水解",   4, 6, BasicHexagram.ZHEN, 2),
    H72(41, "山泽损",   7, 2, BasicHexagram.GEN , 3),
    H54(42, "风雷益",   5, 4, BasicHexagram.XUN , 3),
    H21(43, "泽天夬",   2, 1, BasicHexagram.KUN , 5),
    H15(44, "天风姤",   1, 5, BasicHexagram.QIAN, 1),
    H28(45, "泽地萃",   2, 8, BasicHexagram.DUI , 2),
    H85(46, "地风升",   8, 5, BasicHexagram.ZHEN, 4),
    H26(47, "泽水困",   2, 6, BasicHexagram.DUI , 1),
    H65(48, "水风井",   6, 5, BasicHexagram.ZHEN, 5),
    H23(49, "泽火革",   2, 3, BasicHexagram.KAN , 4),
    H35(50, "火风鼎",   3, 5, BasicHexagram.LI  , 2),
    H44(51, "震为雷",   4, 4, BasicHexagram.ZHEN, 0),
    H77(52, "艮为山",   7, 7, BasicHexagram.GEN , 0),
    H57(53, "风山渐",   5, 7, BasicHexagram.GEN , 7),
    H42(54, "雷泽归妹", 4, 2, BasicHexagram.DUI , 7),
    H43(55, "雷火丰",   4, 3, BasicHexagram.KAN , 5),
    H37(56, "火山旅",   3, 7, BasicHexagram.LI  , 1),
    H55(57, "巽为风",   5, 5, BasicHexagram.XUN , 0),
    H22(58, "兑为泽",   2, 2, BasicHexagram.DUI , 0),
    H56(59, "风水涣",   5, 6, BasicHexagram.LI  , 5),
    H62(60, "水泽节",   6, 2, BasicHexagram.KAN , 1),
    H52(61, "风泽中孚", 5, 2, BasicHexagram.GEN , 6),
    H47(62, "雷山小过", 4, 7, BasicHexagram.DUI , 6),
    H63(63, "水火既济", 6, 3, BasicHexagram.KAN , 3),
    H36(64, "火水未济", 3, 6, BasicHexagram.LI  , 3);

    int value;
    String name;
    int upper;
    int under;
    BasicHexagram palace;
    int generation;

    public BasicHexagram getUnder() {
        return BasicHexagram.getByInitValue(under);
    }

    public BasicHexagram getUpper() {
        return BasicHexagram.getByInitValue(upper);
    }

    public String getCode() {
        return getUnder().getCode() + getUpper().getCode();
    }

    public YinYang getYao(int y) {
        return YinYang.getByValue(Integer.parseInt(getCode().substring(y - 1, y)));
    }

    public CompositeHexagram setYao(int y, YinYang yao) {
        String code = getCode();
        String alterCode = code.substring(0, y - 1) + yao.getValue() + code.substring(y);
        return getByCode(alterCode);
    }

    public CompositeHexagram change(int y) {
        return setYao(y, getYao(y).reverse());
    }

    public HexagramGeneration getGeneration() {return HexagramGeneration.getByValue(generation);}

    public String getGenerationName() {
        return getGeneration().getName();
    }

    public static CompositeHexagram getByUpperAndUnder(int upper, int under) {
        return valueOf("H" + upper + "" + under);
    }

    public static CompositeHexagram getByUpperAndUnder(BasicHexagram upper, BasicHexagram under) {
        return getByUpperAndUnder(upper.getInitValue(), under.getInitValue());
    }

    public static CompositeHexagram getByCode(String code) {
        return getByUpperAndUnder(BasicHexagram.getByCode(code.substring(3)), BasicHexagram.getByCode(code.substring(0, 3)));
    }
}
