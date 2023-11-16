package run.zhinan.zhouyi.classic.fate;

import run.zhinan.zhouyi.classic.common.Gan;
import run.zhinan.zhouyi.classic.common.GanZhi;
import run.zhinan.zhouyi.classic.common.Zhi;

public class FateCodeColumn extends GanZhi {
    ColumnType type;
    Gan        fate;

    public FateCodeColumn(Gan gan, Zhi zhi, ColumnType type, Gan fate) {
        super(gan, zhi);
        this.type = type;
        this.fate = fate;
    }

    public static FateCodeColumn of(GanZhi ganZhi, ColumnType type, Gan fate) {
        return new FateCodeColumn(ganZhi.getGan(), ganZhi.getZhi(), type, fate);
    }

    public FateGod getGanGod() {
        return fate.effect(getGan());
    }

    public FateGod getZhiGod() {
        return fate.effect(getZhi().getGan());
    }
}
