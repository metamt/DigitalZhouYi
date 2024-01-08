package run.zhinan.zhouyi.common;

public enum YinYang {
    YIN(0, "阴"), YANG(1, "阳");

    int value;
    String name;

    YinYang(int value, String name) {
        this.value = value;
        this.name  = name;
    }

    public int getValue() { return value; }

    public String getName() {
        return name;
    }

    public boolean isYang() { return getValue() == YANG.getValue(); }

    public YinYang reverse() {
        return isYang() ? YIN : YANG;
    }

    public static YinYang getByValue(int value) {
        return values()[value];
    }

    public YinYang effect(YinYang other) {
        return YinYang.getByValue((this.value - other.value + 2) % 2);
    }
}
