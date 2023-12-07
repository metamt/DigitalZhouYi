package run.zhinan.zhouyi.classic.fate.fortune;

import run.zhinan.time.ganzhi.GanZhiDateTime;
import run.zhinan.zhouyi.classic.common.GanZhi;
import run.zhinan.zhouyi.classic.fate.ColumnType;
import run.zhinan.zhouyi.classic.fate.FateCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TimeFortune extends BaseFortune implements PeriodFortune {
    public TimeFortune(GanZhi ganZhi, FateCode fateCode, LocalDateTime startTime, LocalDateTime endTime) {
        super(ganZhi, ColumnType.TIME_FORTUNE, fateCode, startTime, endTime);
    }

    public TimeFortune next() {
        return new TimeFortune(roll(1), getFateCode(), endTime, endTime.plusHours(2));
    }

    public static TimeFortune of(LocalDateTime dateTime, FateCode fateCode) {
        GanZhiDateTime ganZhiDateTime = GanZhiDateTime.of(dateTime);
        GanZhi ganZhi = GanZhi.of(ganZhiDateTime.getGanZhiTime());
        LocalDateTime startTime = dateTime.toLocalDate().atTime(dateTime.getHour(), 0).minusHours(1 - dateTime.getHour() % 2);
        LocalDateTime endTime   = startTime.plusHours(2);
        return new TimeFortune(ganZhi, fateCode, startTime, endTime);
    }

    public static List<TimeFortune> list(DailyFortune dailyFortune) {
        List<TimeFortune> timeFortuneList = new ArrayList<>();
        TimeFortune fortune = TimeFortune.of(dailyFortune.startTime, dailyFortune.getFateCode());
        for (int i = 0; i < 12; i++) {
            timeFortuneList.add(fortune);
            fortune = fortune.next();
        }
        return timeFortuneList;
    }

    @Override
    public String getDate() {
        return String.valueOf((startTime.getHour() +  1) % 24);
    }

    @Override
    public String getAge() {
        return String.valueOf((startTime.getHour() + 23) % 24);
    }

    @Override
    public DailyFortune getParent() {
        return DailyFortune.of(this.startTime.plusHours(1), getFateCode());
    }
}
