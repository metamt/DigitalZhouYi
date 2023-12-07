package run.zhinan.zhouyi.classic.fate.fortune;

import run.zhinan.time.ganzhi.GanZhiDate;
import run.zhinan.time.solar.SolarTerm;
import run.zhinan.zhouyi.classic.common.GanZhi;
import run.zhinan.zhouyi.classic.fate.ColumnType;
import run.zhinan.zhouyi.classic.fate.FateCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class YearFortune extends BaseFortune implements PeriodFortune {
    public YearFortune(GanZhi ganZhi, FateCode fateCode, LocalDateTime startTime, LocalDateTime endTime) {
        super(ganZhi, ColumnType.YEAR_FORTUNE, fateCode, startTime, endTime);
    }

    public YearFortune next() {
        return new YearFortune(roll(1), getFateCode(), endTime, SolarTerm.J01_LICHUN.of(startTime.getYear()).getDateTime());
    }

    public static YearFortune of(LocalDateTime dateTime, FateCode fateCode) {
        GanZhiDate ganZhiDate = GanZhiDate.of(dateTime.toLocalDate());
        GanZhi ganZhi = GanZhi.of(ganZhiDate.getGanZhiYear());
        LocalDateTime startTime = SolarTerm.J01_LICHUN.of(dateTime.getYear()).getDateTime();
        LocalDateTime   endTime = SolarTerm.J01_LICHUN.of(dateTime.getYear() + 1).getDateTime();
        return new YearFortune(ganZhi, fateCode, startTime, endTime);
    }

    /**
     * 列出一步大运中的所有年运
     * @param decadeFortune 要列出年运的大运
     * @return 该大运十年间的所有年运，从大运起始年开始，到大运终止年为止
     */
    public static List<YearFortune> list(DecadeFortune decadeFortune) {
        List<YearFortune> yearFortuneList = new ArrayList<>();
        YearFortune fortune = of(decadeFortune.startTime, decadeFortune.getFateCode());
        for (int y = decadeFortune.startTime.getYear(); y < decadeFortune.endTime.getYear(); y++) {
            yearFortuneList.add(fortune);
            fortune = fortune.next();
        }
        return yearFortuneList;
    }

    /**
     * 列出一个人一生的百年年运
     * @param fateCode 八字
     * @return 一生的年运
     */
    public static List<YearFortune> list(FateCode fateCode) {
        List<YearFortune> yearFortuneList = new ArrayList<>();
        List<DecadeFortune> decadeFortuneList = DecadeFortune.list(fateCode);
        for (DecadeFortune decadeFortune : decadeFortuneList) {
            yearFortuneList.addAll(list(decadeFortune));
        }
        return yearFortuneList;
    }

    @Override
    public DecadeFortune getParent() {
        return DecadeFortune.of(this.endTime, getFateCode());
    }
}
