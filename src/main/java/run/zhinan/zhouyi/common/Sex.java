package run.zhinan.zhouyi.common;

public enum Sex {
    FEMALE(0, "女"), MALE(1, "男");

    int value;
    String name;

    Sex(int value, String name) {
        this.value = value;
        this.name  = name;
    }

    public int getValue() { return value; }

    public String getName() {
        return name;
    }

    public YinYang getYinYang() {
        return YinYang.getByValue(value);
    }

    public boolean isMale() {
        return MALE.value == this.value;
    }

    public static Sex getByValue(int value) {
        return values()[value];
    }
}
