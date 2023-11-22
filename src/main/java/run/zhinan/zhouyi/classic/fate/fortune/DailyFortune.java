package run.zhinan.zhouyi.classic.fate.fortune;

import run.zhinan.time.ganzhi.GanZhiDate;
import run.zhinan.time.lunar.LunarDate;
import run.zhinan.time.lunar.LunarDateTime;
import run.zhinan.zhouyi.classic.common.GanZhi;
import run.zhinan.zhouyi.classic.fate.ColumnType;
import run.zhinan.zhouyi.classic.fate.FateCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DailyFortune extends Fortune {
    public DailyFortune(GanZhi ganZhi, FateCode bazi, LocalDateTime startTime, LocalDateTime endTime) {
        super(ganZhi, ColumnType.DAILY_FORTUNE, bazi, startTime, endTime);
    }

    public DailyFortune next() {
        return new DailyFortune(this.roll(1), getFateCode(), endTime, endTime.plusDays(1));
    }

    public static DailyFortune of(LocalDateTime dateTime, FateCode fateCode) {
        LocalDate  date = dateTime.toLocalDate();
        GanZhiDate ganZhiDate = GanZhiDate.of(date);
        GanZhi ganZhi = GanZhi.of(ganZhiDate.getGanZhiDay());
        return new DailyFortune(ganZhi, fateCode, date.atTime(0,0), date.plusDays(1).atTime(0, 0));
    }

    public static List<DailyFortune> list(MonthFortune monthFortune) {
        List<DailyFortune> dailyFortuneList = new ArrayList<>();
        DailyFortune fortune = DailyFortune.of(monthFortune.startTime, monthFortune.getFateCode());
        while (fortune.endTime.isBefore(monthFortune.endTime)){
            dailyFortuneList.add(fortune);
            fortune = fortune.next();
        }
        return dailyFortuneList;
    }

    @Override
    public String getDate() {
        return startTime.getMonthValue() + "." + startTime.getDayOfMonth();
    }

    @Override
    public String getAge() {
        String dateString = LunarDate.of(startTime.toLocalDate()).toString();
        return dateString.substring(dateString.length() - 2);
    }

    @Override
    protected Fortune getParent() {
        return MonthFortune.of(startTime.plusDays(1), getFateCode());
    }
}
