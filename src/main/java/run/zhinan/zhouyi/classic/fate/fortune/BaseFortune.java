package run.zhinan.zhouyi.classic.fate.fortune;

import lombok.Getter;
import run.zhinan.zhouyi.classic.common.GanZhi;
import run.zhinan.zhouyi.classic.fate.ColumnType;
import run.zhinan.zhouyi.classic.fate.FateCode;
import run.zhinan.zhouyi.classic.fate.FateCodeColumn;

import java.time.LocalDateTime;

@Getter
public abstract class BaseFortune extends FateCodeColumn implements PeriodFortune {
    LocalDateTime startTime;
    LocalDateTime endTime;

    public BaseFortune(GanZhi ganZhi, ColumnType type, FateCode fateCode, LocalDateTime startTime, LocalDateTime endTime) {
        super(ganZhi, type, fateCode);
        this.startTime = startTime;
        this.endTime   = endTime;
    }

    public int getSelfScore() {
        return new Double(40 * (isGanGodGood() ? 0.8 : 0.3) + 60 * (isZhiGodGood() ? 0.8 : 0.3)).intValue();
    }

    public int getScore() {
        int score = getSelfScore();
        PeriodFortune parent = getParent();
        int parentScore = parent == null ? score : parent.getScore();
        return new Double(parentScore * 0.4 + score * 0.6).intValue();
    }

    public FortuneGoodLevel getGoodLevel() {
        return FortuneGoodLevel.getByScore(getSelfScore());
    }

    public String getDate() {
        return String.valueOf(startTime.getYear());
    }

    public String getAge() {
        return String.valueOf(startTime.getYear() - getFateCode().getBirthday().getYear());
    }

    public abstract PeriodFortune getParent();

    @Override
    protected FateCode getFateCode() {
        return super.getFateCode();
    }

    @Override
    public String toString() {
        return getName() + " (" + getDate() + " - " + getAge() + " - " + getScore() + " - " + getGoodLevel().getName() + ")";
    }
}
