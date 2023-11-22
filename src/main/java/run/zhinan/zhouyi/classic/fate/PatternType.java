package run.zhinan.zhouyi.classic.fate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PatternType {
    ADJUST(0, "扶抑格"), FOLLOW(1,"从格");

    int value;
    String name;
}
