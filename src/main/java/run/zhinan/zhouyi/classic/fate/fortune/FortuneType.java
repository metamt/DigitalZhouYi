package run.zhinan.zhouyi.classic.fate.fortune;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FortuneType {
    DECADE_FORTUNE("十年"),
    YEAR_FORTUNE  ("一年"),
    MONTH_FORTUNE ("一月"),
    DAILY_FORTUNE ("一天"),
    TIME_FORTUNE  ("时辰");

    String unit;

    public static FortuneType getByValue(int value) {
        return values()[value];
    }
}
