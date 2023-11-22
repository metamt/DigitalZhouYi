package run.zhinan.zhouyi.classic.fate;

import lombok.Getter;
import run.zhinan.zhouyi.classic.common.GanZhi;

@Getter
public class FateCodeColumn extends GanZhi {
    ColumnType type;
    FateCode   fateCode;
    FatePattern pattern;

    public FateCodeColumn(GanZhi ganZhi, ColumnType type, FateCode bazi) {
        super(ganZhi.getGan(), ganZhi.getZhi());
        this.type = type;
        this.fateCode = bazi;
    }

    public static FateCodeColumn of(GanZhi ganZhi, ColumnType type, FateCode bazi) {
        return new FateCodeColumn(ganZhi, type, bazi);
    }

    public FateGod getGanGod() {
        return getFateCode().getFate().effect(getGan());
    }

    public FateGod getZhiGod() {
        return getFateCode().getFate().effect(getZhi().getGan());
    }

    public boolean isGanGodGood() {
        return getPattern().isGood(getGan().getWuXing());
    }

    public boolean isZhiGodGood() {
        return getPattern().isGood(getZhi().getWuXing());
    }

    protected FateCode getFateCode() { return fateCode; }

    protected FatePattern getPattern() {
        if (pattern == null) pattern = FatePattern.of(getFateCode());
        return pattern;
    }
}
