package run.zhinan.zhouyi.classic.fate.energy;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnergyType {
    TOO_STRONG  ( 2, "从强", true , true ),
    STRONG      ( 1, "身强", true , false),
    BALANCE     ( 0, "平衡", null , false),
    WEAK        (-1, "身弱", false, false),
    TOO_WEAK    (-2, "从弱", false, true );

    int value;
    String name;
    Boolean strong;
    Boolean follow;

    public static EnergyType of(boolean strong, boolean follow) {
        return values()[2 - (strong ? 1 : -1) * (follow ? 2 : 1)];
    }
}
