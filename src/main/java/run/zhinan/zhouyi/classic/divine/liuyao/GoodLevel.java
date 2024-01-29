package run.zhinan.zhouyi.classic.divine.liuyao;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GoodLevel {
    GREAT("旺极", 250), GOOD("偏旺", 150), OK("平相", 50), BAD("偏衰", -50), WORST("弱极", -1000);

    final String name;
    final int    score;

    public static GoodLevel getByScore(int score) {
        GoodLevel result = null;
        for (GoodLevel level : values()) {
            if (score > level.score) {
                result = level;
                break;
            }
        }
        return result;
    }
}
