package run.zhinan.zhouyi.classic.fate.energy;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnergyType {
    TOO_STRONG  ( 2, "从强"),
    STRONG      ( 1, "身强"),
    BALANCE     ( 0, "平衡"),
    WEAK        (-1, "身弱"),
    TOO_WEAK    (-2, "从弱");

    int value;
    String name;
}
