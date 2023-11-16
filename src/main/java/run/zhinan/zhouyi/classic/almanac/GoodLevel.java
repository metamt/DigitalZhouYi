package run.zhinan.zhouyi.classic.almanac;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GoodLevel {
    GOOD(0, "吉"), OK(1, "平"), BAD(2, "凶");

    int value;
    String name;

    @Override
    public String toString() {
        return name;
    }
}
