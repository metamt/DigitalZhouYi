package run.zhinan.zhouyi.classic.divine.liuyao;

import lombok.Getter;
import run.zhinan.zhouyi.classic.common.Gan;
import run.zhinan.zhouyi.classic.common.GanZhi;
import run.zhinan.zhouyi.classic.common.Zhi;
import run.zhinan.zhouyi.common.WuXing;
import run.zhinan.zhouyi.common.YinYang;

@Getter
public class HexagramYao extends GanZhi {
    int      data;
    YinYang  yinYang;
    WuXing   wuXing;
    Relation relation;
    Position position;

    HexagramYao hidden;

    boolean self;
    boolean ying;
    boolean change;
    boolean fly;

    public HexagramYao(Gan gan, Zhi zhi, YinYang yinYang, WuXing wuXing, Relation relation, Position position) {
        super(gan, zhi);
        this.yinYang  = yinYang;
        this.wuXing   = wuXing;
        this.relation = relation;
        this.position = position;
    }
}
