package run.zhinan.zhouyi.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WuXingEffect {
    GIVE(0, "生"),
    HELP(1, "同"),
    LEAK(2, "泄"),
    COST(3, "耗"),
    CURB(4, "克");

    int value;
    String name;

    public static WuXingEffect of(WuXing self, WuXing other) {
        switch ((other.getValue() - self.getValue() + 5) % 5) {
            case 0:
                return HELP;
            case 1:
                return LEAK;
            case 2:
                return COST;
            case 3:
                return CURB;
            default:
            case 4:
                return GIVE;
        }
    }

    public WuXing of(WuXing wuXing) {
        return WuXing.getByValue((this.value - 1 + wuXing.getValue() + 5) % 5);
    }
}
