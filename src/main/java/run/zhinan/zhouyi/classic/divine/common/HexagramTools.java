package run.zhinan.zhouyi.classic.divine.common;

public class HexagramTools {
    // 转为互卦
    public static CompositeHexagram toNuclearHexagram(CompositeHexagram hexagram) {
        String code = hexagram.getCode();
        String alterCode = code.substring(1, 4) + code.substring(2, 5);
        return CompositeHexagram.getByCode(alterCode);
    }

    // 转为错卦
    public static CompositeHexagram toChangingHexagram(CompositeHexagram hexagram) {
        return hexagram;
    }

    // 转为综卦
    public static CompositeHexagram toOppositeHexagram(CompositeHexagram hexagram) {
        return hexagram;
    }

    // 转为互卦
    public static CompositeHexagram toInterrelatedHexagram(CompositeHexagram hexagram) {
        return hexagram;
    }

    // 转为杂卦
    public static CompositeHexagram toMixedHexagram(CompositeHexagram hexagram) {
        return hexagram;
    }
}
