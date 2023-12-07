package run.zhinan.zhouyi.classic.fate.fortune;

import run.zhinan.time.ganzhi.GanZhiDate;
import run.zhinan.time.solar.SolarTerm;
import run.zhinan.zhouyi.classic.common.GanZhi;
import run.zhinan.zhouyi.classic.fate.ColumnType;
import run.zhinan.zhouyi.classic.fate.FateCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MonthFortune extends BaseFortune implements PeriodFortune {
    public MonthFortune(GanZhi ganZhi, FateCode fateCode, LocalDateTime startTime, LocalDateTime endTime) {
        super(ganZhi, ColumnType.MONTH_FORTUNE, fateCode, startTime, endTime);
    }

    public MonthFortune next() {
        return new MonthFortune(roll(1), getFateCode(), endTime, SolarTerm.getNextMajorSolarTerm(endTime.plusDays(15)).getDateTime());
    }

    public static MonthFortune of(LocalDateTime dateTime, FateCode fateCode) {
        GanZhiDate ganZhiDate = GanZhiDate.of(dateTime.toLocalDate());
        GanZhi ganZhi = GanZhi.of(ganZhiDate.getGanZhiMonth());
        LocalDateTime startTime = SolarTerm.getLastMajorSolarTerm(dateTime).getDateTime();
        LocalDateTime   endTime = SolarTerm.getNextMajorSolarTerm(dateTime).getDateTime();
        return new MonthFortune(ganZhi, fateCode, startTime, endTime);
    }

    public static List<MonthFortune> list(YearFortune yearFortune) {
        List<MonthFortune> monthFortuneList = new ArrayList<>();
        MonthFortune fortune = MonthFortune.of(yearFortune.startTime.plusDays(1), yearFortune.getFateCode());
        for (int i = 0; i < 12; i++) {
            monthFortuneList.add(fortune);
            fortune = fortune.next();
        }
        return monthFortuneList;
    }

    @Override
    public String getDate() {
        return startTime.getMonthValue() + "." + startTime.getDayOfMonth();
    }

    @Override
    public String getAge() {
        return SolarTerm.getLastMajorSolarTerm(startTime.plusDays(1)).getName();
    }

    @Override
    public YearFortune getParent() {
        return YearFortune.of(endTime, getFateCode());
    }
}
