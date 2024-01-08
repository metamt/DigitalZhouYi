package run.zhinan.zhouyi.classic.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EffectType {
    Generate("生"), Control("克"),
    Combine ("合"), Gather ("会"),
    Conflict("冲"), Punish ("刑"),
    Harm    ("害"), Break  ("破");

    String name;

    public int getValue() {return ordinal();}
}
