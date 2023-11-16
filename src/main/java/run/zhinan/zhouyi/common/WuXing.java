package run.zhinan.zhouyi.common;

public class WuXing implements Comparable<WuXing> {
    public final static WuXing WOOD  = new WuXing(0, "木");
    public final static WuXing FIRE  = new WuXing(1, "火");
    public final static WuXing EARTH = new WuXing(2, "土");
    public final static WuXing METAL = new WuXing(3, "金");
    public final static WuXing WATER = new WuXing(4, "水");

    public static WuXing[] values() {
        return new WuXing[] {WOOD, FIRE, EARTH, METAL, WATER};
    }

    int value;
    String name;

    WuXing(int value, String name) {
        this.value = value;
        this.name  = name;
    }

    public int getValue() { return value; }

    public String getName() {
        return name;
    }

    public static WuXing getByValue(int value) {
        return values()[value];
    }

    public WuXingEffect effect(WuXing other) {
        return WuXingEffect.of(this, other);
    }

    @Override
    public int compareTo(WuXing wx) {
        return this.value - wx.value;
    }
}
