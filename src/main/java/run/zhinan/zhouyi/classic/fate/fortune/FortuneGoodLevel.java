package run.zhinan.zhouyi.classic.fate.fortune;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FortuneGoodLevel {
    GREAT("极旺", 80), GOOD("较好", 60), OK("中平", 30), BAD("较低", 0);

    String name;
    int score;

    @Override
    public String toString() {
        return name;
    }

    public static FortuneGoodLevel getByScore(int score) {
        FortuneGoodLevel result = FortuneGoodLevel.BAD;
        for (FortuneGoodLevel goodLevel : values()) {
            if (score > goodLevel.score) {
                result = goodLevel;
                break;
            }
        }
        return result;
    }
}
