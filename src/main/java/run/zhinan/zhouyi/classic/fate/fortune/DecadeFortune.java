package run.zhinan.zhouyi.classic.fate.fortune;

import run.zhinan.time.solar.SolarTerm;
import run.zhinan.zhouyi.classic.common.GanZhi;
import run.zhinan.zhouyi.classic.fate.ColumnType;
import run.zhinan.zhouyi.classic.fate.FateCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DecadeFortune extends Fortune {
    FortuneDirection direction;

    public DecadeFortune(GanZhi ganZhi, FateCode bazi, LocalDateTime startTime, LocalDateTime endTime) {
        super(ganZhi, ColumnType.DECADE_FORTUNE, bazi, startTime, endTime);
        direction = FortuneDirection.of(bazi);
    }

    public DecadeFortune next() {
        return new DecadeFortune(this.roll(direction.value), getFateCode(), endTime.plusDays(1),
                SolarTerm.J01_LICHUN.of(endTime.getYear() + 10).getDateTime());
    }

    public static DecadeFortune getFirstDecadeFortune(FateCode fateCode) {
        FortuneStartTime startTime = FortuneStartTime.of(fateCode);
        return new DecadeFortune(fateCode.getColumn(ColumnType.MONTH), fateCode, fateCode.getBirthday(), startTime.getFortuneTime());

    }

    public static List<DecadeFortune> list(FateCode fateCode) {
        List<DecadeFortune> decadeFortuneList = new ArrayList<>();
        decadeFortuneList.add(getFirstDecadeFortune(fateCode).next());
        for (int i = 0; i < 9; i++) {
            decadeFortuneList.add(decadeFortuneList.get(i).next());
        }
        return decadeFortuneList;
    }

    public static DecadeFortune of(LocalDateTime dateTime, FateCode fateCode) {
        DecadeFortune fortune = getFirstDecadeFortune(fateCode);
        while (fortune.endTime.isBefore(dateTime)) {
            fortune = fortune.next();
        }
        return fortune;
    }

    @Override
    protected Fortune getParent() {
        return null;
    }
}