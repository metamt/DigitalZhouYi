package run.zhinan.zhouyi.classic.fate.fortune;

import lombok.AllArgsConstructor;
import lombok.Getter;
import run.zhinan.zhouyi.classic.fate.ColumnType;
import run.zhinan.zhouyi.classic.fate.FateCode;

@Getter
@AllArgsConstructor
public enum FortuneDirection {
    FORWARD(1, "正序"), BACKWARD(-1, "倒序");

    int value;
    String name;

    public static FortuneDirection of(FateCode fateCode) {
        return fateCode.getSex().getYinYang().equals(fateCode.getColumn(ColumnType.YEAR).getGan().getYinYang()) ? FORWARD : BACKWARD;
    }

    public boolean isForward() {
        return value == FORWARD.value;
    }
}
