package run.zhinan.zhouyi.classic.divine.liuyao;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Relation {
    Parent    ("父母"),
    Brother   ("兄弟"),
    Descendant("子孙"),
    Wife      ("妻财"),
    Officer   ("官鬼"),

    Month     ("月建"),
    Day       ("日建"),
    Empty     ("旬空");

    String name;

    public int getValue() {
        return ordinal();
    }

    public static Relation getByValue(int value) {
        return values()[value];
    }
}
