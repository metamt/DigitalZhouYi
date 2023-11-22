package run.zhinan.zhouyi.classic.fate.fortune;

import lombok.Getter;
import run.zhinan.time.solar.SolarTerm;
import run.zhinan.zhouyi.classic.fate.FateCode;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
public class FortuneStartTime {
    int year, month, day;
    String fortuneAge;
    LocalDateTime fortuneTime;
    FortuneDirection direction;

    /**
     * 计算大运的排列顺序
     * 当年份的阴阳与性别阴阳相同时正序，
     * 当年分的阴阳与性别阴阳相反时倒序。
     * 阳年男性正序，阳年女性倒序
     * 阴年男性倒序，阴年女性正序
     */
    public static FortuneDirection getDirection(FateCode fateCode) {
        return FortuneDirection.of(fateCode);
    }

    /**
     * 计算从生日到节气的时间
     * 正序的计算到下一个节气的时间
     * 倒序的计算到上一个节气的时间
     */
    public static long calculateFortuneHour(FateCode fateCode) {
        long hours;
        LocalDateTime birthday = fateCode.getBirthday();
        if (getDirection(fateCode).isForward()) {
            LocalDateTime solarTermDateTime = SolarTerm.getNextMajorSolarTerm(birthday).getDateTime();
            hours = Duration.between(birthday, solarTermDateTime).toHours();
        } else {
            LocalDateTime solarTermDateTime = SolarTerm.getLastMajorSolarTerm(birthday).getDateTime();
            hours = Duration.between(solarTermDateTime, birthday).toHours();
        }
        return hours;
    }

    /**
     * 由生日到节气的时间计算起运时间
     * 按照 1小时5天，6小时1个月，72小时1年 折算出起运时间
     */
    public static FortuneStartTime of(FateCode fateCode) {
        long hours = calculateFortuneHour(fateCode);
        FortuneStartTime startTime = new FortuneStartTime();
        startTime.year  = (int) hours / 72;
        startTime.month = (int)(hours % 72) / 6;
        startTime.day   = (int)(hours % 6 ) * 5;
        startTime.direction   = getDirection(fateCode);
        startTime.fortuneAge  = startTime.year + "年" + startTime.month + "个月" + startTime.day + "天";
        startTime.fortuneTime = fateCode.getBirthday().toLocalDate()
                .plusYears(startTime.year).plusMonths(startTime.month).plusDays(startTime.day)
                .atTime(0, 0);
        return startTime;
    }
}
