package run.zhinan.zhouyi.classic.fate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ColumnType {
    YEAR (0, "年柱"),
    MONTH(1, "月柱"),
    DAY  (2, "日柱"),
    TIME (3, "时柱"),

    DECADE_FORTUNE(4, "大运"),
    YEAR_FORTUNE  (5, "年运"),
    MONTH_FORTUNE (6, "月运"),
    DAILY_FORTUNE (7, "日运"),
    TIME_FORTUNE  (8, "时运");

    public static ColumnType[] originals = {YEAR, MONTH, DAY, TIME};

    int value;
    String name;
}
