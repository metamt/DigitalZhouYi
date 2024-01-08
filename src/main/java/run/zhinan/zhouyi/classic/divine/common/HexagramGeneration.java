package run.zhinan.zhouyi.classic.divine.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HexagramGeneration {
    SELF  ("本宫"),
    FIRST ("初世"),
    SECOND("二世"),
    THIRD ("三世"),
    FORTH ("四世"),
    FIFTH ("五世"),
    WANDER("游魂"),
    RETURN("归魂");

    String name;

    public int getValue() {return ordinal();}

    public static HexagramGeneration getByValue(int value) {
        return values()[value];
    }
}
