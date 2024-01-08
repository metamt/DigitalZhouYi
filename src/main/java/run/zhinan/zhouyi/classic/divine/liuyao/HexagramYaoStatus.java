package run.zhinan.zhouyi.classic.divine.liuyao;

import lombok.Getter;

@Getter
public class HexagramYaoStatus {
    HexagramYao yao;
    int score;

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
}
