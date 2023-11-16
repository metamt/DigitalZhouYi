package run.zhinan.zhouyi.classic.almanac;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DutyWay {
    YELLOW(0, "黄道"), BLACK(2, "黑道");

    int value;
    String name;
}
