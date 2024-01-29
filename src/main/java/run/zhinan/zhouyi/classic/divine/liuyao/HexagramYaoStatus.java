package run.zhinan.zhouyi.classic.divine.liuyao;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class HexagramYaoStatus {
    HexagramYao yao;
    int score;
    List<EffectStatus> effects = new ArrayList<>();

    boolean beConflicted;
    boolean beControlled;
    boolean beBackGenerated;
    boolean beBackControlled;

    public HexagramYaoStatus(HexagramYao yao, int score) {
        this.yao = yao;
        this.score = score;
    }

    public HexagramYaoStatus(HexagramYao yao) {
        this(yao, 100);
    }

    public HexagramYaoStatus copy() {
        return new HexagramYaoStatus(this.yao, this.score);
    }

    public GoodLevel getLevel() {
        return GoodLevel.getByScore(score);
    }

    public HexagramYao yao() {
        return getYao();
    }

    private HexagramYao getYao() {
        return yao;
    }
}
