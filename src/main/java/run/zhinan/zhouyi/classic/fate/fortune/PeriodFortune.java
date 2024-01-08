package run.zhinan.zhouyi.classic.fate.fortune;

import run.zhinan.zhouyi.classic.fate.ColumnType;
import run.zhinan.zhouyi.classic.fate.FateCode;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface PeriodFortune {
    LocalDateTime getStartTime();
    LocalDateTime getEndTime();
    PeriodFortune next();
    PeriodFortune getParent();
    int getSelfScore();
    int getScore();
    FortuneGoodLevel getGoodLevel();

    static List<? extends BaseFortune> getFortunes(LocalDateTime dateTime, FateCode fateCode, ColumnType columnType) {
        List<BaseFortune> fortuneList = new ArrayList<>();
        PeriodFortune fortune = of(dateTime, fateCode, columnType);
        while (fortune != null){
            fortuneList.add((BaseFortune) fortune);
            fortune = fortune.getParent();
        }
        Collections.reverse(fortuneList);
        return fortuneList;
    }

    static PeriodFortune of(LocalDateTime dateTime, FateCode fateCode, ColumnType columnType) {
        PeriodFortune fortune = null;
        switch (columnType) {
            case DECADE_FORTUNE:
                fortune = DecadeFortune.of(dateTime, fateCode);
                break;
            case YEAR_FORTUNE:
                fortune = YearFortune.of(dateTime, fateCode);
                break;
            case MONTH_FORTUNE:
                fortune = MonthFortune.of(dateTime, fateCode);
                break;
            case DAILY_FORTUNE:
                fortune = DailyFortune.of(dateTime, fateCode);
                break;
            case TIME_FORTUNE:
                fortune = TimeFortune.of(dateTime, fateCode);
        }
        return fortune;
    }

    static FateCode getFateCode(BaseFortune fortune) {
        return fortune.getFateCode();
    }

    static LocalDateTime getDateTime(PeriodFortune fortune) {
        return fortune.getStartTime().plusSeconds(Duration.between(fortune.getStartTime(), fortune.getEndTime()).getSeconds() / 2);
    }
}
