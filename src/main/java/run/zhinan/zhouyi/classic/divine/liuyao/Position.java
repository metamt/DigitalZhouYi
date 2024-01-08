package run.zhinan.zhouyi.classic.divine.liuyao;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Position {
    FIRST      ("初爻"),
    SECOND     ("二爻"),
    THIRD      ("三爻"),
    FORTH      ("四爻"),
    FIFTH      ("五爻"),
    SIXTH      ("上爻"),
    HIDDEN     ("伏爻"),
    ENVIRONMENT("环境");

    String name;

    public int getValue() {return ordinal();}

    public static Position getByValue(int value) {
        return values()[value];
    }

    public static Position of(run.zhinan.zhouyi.classic.divine.common.Position position) {
        return getByValue(position.getValue());
    }

}
