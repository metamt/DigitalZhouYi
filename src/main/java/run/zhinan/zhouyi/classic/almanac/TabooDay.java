package run.zhinan.zhouyi.classic.almanac;

import run.zhinan.time.ganzhi.Gan;
import run.zhinan.time.ganzhi.GanZhi;
import run.zhinan.time.ganzhi.GanZhiDate;
import run.zhinan.time.ganzhi.Zhi;
import run.zhinan.time.lunar.LunarDate;
import run.zhinan.time.solar.SolarTerm;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class TabooDay {
    public static boolean isBadDay(LocalDate date) {
        LunarDate  lunarDate  = LunarDate .of(date);
        return isFourExtinctionDay(date) || isFourDetachDay(date) || isMrYangTabooDay(lunarDate) || isMonthlyTabooDay(lunarDate);
    }

    /**
     * 四离日
     * 春分，夏至，秋分，冬至的前一天为四离日
     * @param date  要测试的日期
     * @return 是否为四离日
     */
    public static boolean isFourDetachDay(LocalDate date) {
        boolean result = false;
        SolarTerm[] solarTerms = {SolarTerm.Z02_CHUNFEN, SolarTerm.Z05_XIAZHI, SolarTerm.Z08_QIUFEN, SolarTerm.Z11_DONGZHI};
        for (SolarTerm solarTerm : solarTerms) {
            if (Duration.between(date.atTime(0,0), solarTerm.of(date.getYear()).getDate().atTime(0, 0)).toDays() == 1L) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * 四绝日
     * 立春，立夏，立秋，立冬的前一天为四绝日
     * @param date  要测试的日期
     * @return 是否为四绝日
     */
    public static boolean isFourExtinctionDay(LocalDate date) {
        boolean result = false;
        SolarTerm[] solarTerms = {SolarTerm.J01_LICHUN, SolarTerm.J04_LIXIA, SolarTerm.J07_LIQIU, SolarTerm.J10_LIDONG};
        for (SolarTerm solarTerm : solarTerms) {
            if (Duration.between(date.atTime(0,0), solarTerm.of(date.getYear()).getDate().atTime(0, 0)).toDays() == 1L) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * 四废日
     * 春天（寅卯辰月）的 庚申、辛酉日
     * 夏天（巳午未月）的 壬子、癸亥日
     * 秋天（申酉戌月）的 甲寅、乙卯日
     * 冬天（亥子丑月）的 丙午、丁巳日
     * 为四废日
     * @param date  要测试的日期
     * @return 是否为四废日
     */
    public static boolean isFourUselessDay(LocalDate date) {
        return isFourUselessDay(GanZhiDate.of(date));
    }
    public static boolean isFourUselessDay(GanZhiDate date) {
        int month = date.getGanZhiMonth().getZhi().getValue();
        int day   = date.getGanZhiDay().getValue();
        return  ((month == Zhi.YIN .getValue() || month == Zhi.MAO.getValue() || month == Zhi.CHEN.getValue()) && (day == GanZhi.getByName("庚申").getValue() || day == GanZhi.getByName("辛酉").getValue())) ||
                ((month == Zhi.SI  .getValue() || month == Zhi.WU .getValue() || month == Zhi.WEI .getValue()) && (day == GanZhi.getByName("壬子").getValue() || day == GanZhi.getByName("癸亥").getValue())) ||
                ((month == Zhi.SHEN.getValue() || month == Zhi.YOU.getValue() || month == Zhi.XU  .getValue()) && (day == GanZhi.getByName("甲寅").getValue() || day == GanZhi.getByName("乙卯").getValue())) ||
                ((month == Zhi.HAI .getValue() || month == Zhi.ZI .getValue() || month == Zhi.CHOU.getValue()) && (day == GanZhi.getByName("丙午").getValue() || day == GanZhi.getByName("丁巳").getValue()));
    }

    /**
     * 红煞日
     * 寅申巳亥月 的 酉日
     * 子午卯酉月 的 巳日
     * 辰戌丑未月 的 丑日
     * 为红煞日
     * @param date  要测试的日期
     * @return 是否为红煞日
     */
    public static boolean isRedEvilDay(LocalDate date) {
        return isRedEvilDay(GanZhiDate.of(date));
    }
    public static boolean isRedEvilDay(GanZhiDate date) {
        int month = date.getGanZhiMonth().getZhi().getValue();
        int day   = date.getGanZhiDay()  .getZhi().getValue();
        return  ((month == Zhi.YIN .getValue() || month == Zhi.SHEN.getValue() || month == Zhi.SI  .getValue() || month == Zhi.HAI.getValue()) && day == Zhi.YOU .getValue()) ||
                ((month == Zhi.ZI  .getValue() || month == Zhi.WU  .getValue() || month == Zhi.MAO .getValue() || month == Zhi.YOU.getValue()) && day == Zhi.SI  .getValue()) ||
                ((month == Zhi.CHEN.getValue() || month == Zhi.XU  .getValue() || month == Zhi.CHOU.getValue() || month == Zhi.WEI.getValue()) && day == Zhi.CHOU.getValue());
    }

    /**
     * 黄煞日
     * 寅申巳亥月 的 午日
     * 子午卯酉月 的 寅日
     * 辰戌丑未月 的 子日
     * 为黄煞日
     * @param date  要测试的日期
     * @return 是否为黄煞日
     */
    public static boolean isYellowEvilDay(LocalDate  date) {
        return isYellowEvilDay(GanZhiDate.of(date));
    }
    public static boolean isYellowEvilDay(GanZhiDate date) {
        int month = date.getGanZhiMonth().getZhi().getValue();
        int day   = date.getGanZhiDay().getZhi().getValue();
        return  ((month == Zhi.YIN .getValue() || month == Zhi.SHEN.getValue() || month == Zhi.SI  .getValue() || month == Zhi.HAI.getValue()) && day == Zhi.WU .getValue()) ||
                ((month == Zhi.ZI  .getValue() || month == Zhi.WU  .getValue() || month == Zhi.MAO .getValue() || month == Zhi.YOU.getValue()) && day == Zhi.YIN.getValue()) ||
                ((month == Zhi.CHEN.getValue() || month == Zhi.XU  .getValue() || month == Zhi.CHOU.getValue() || month == Zhi.WEI.getValue()) && day == Zhi.ZI .getValue());
    }

    /**
     * 绝烟火日
     * 正五九月 的 丁卯日
     * 二六十月 的 甲子日
     * 三七冬月 的 癸酉日
     * 四八腊月 的 庚午日
     * 绝烟火日
     * @param date  要测试的日期
     * @return 是否为绝烟火日
     */
    public static boolean isNoCookDay(LocalDate date) {
        return isNoCookDay(GanZhiDate.of(date));
    }
    public static boolean isNoCookDay(GanZhiDate date) {
        int month = date.getMonth();
        int day   = date.getGanZhiDay().getValue();
        return  ((month == 1 || month == 5 || month ==  9) && day == GanZhi.getByName("丁卯").getValue()) ||
                ((month == 2 || month == 6 || month == 10) && day == GanZhi.getByName("甲子").getValue()) ||
                ((month == 3 || month == 7 || month == 11) && day == GanZhi.getByName("癸酉").getValue()) ||
                ((month == 4 || month == 8 || month == 12) && day == GanZhi.getByName("庚午").getValue());
    }

    /**
     * 十恶大败日
     * 日逢甲辰、乙巳、丙申、丁亥、戊戌、己丑、庚辰、辛巳、壬申、癸亥 为十恶大败日
     * @param date  要测试的日期
     * @return 是否为十恶大败日
     */
    public static boolean isTenVeryBadDay(LocalDate  date) {
        return isTenVeryBadDay(GanZhiDate.of(date));
    }
    public static boolean isTenVeryBadDay(GanZhiDate date) {
        return Arrays.asList( GanZhi.getByName("甲辰").getValue(), GanZhi.getByName("乙巳").getValue(), GanZhi.getByName("丙申").getValue(),
                GanZhi.getByName("丁亥").getValue(), GanZhi.getByName("戊戌").getValue(), GanZhi.getByName("己丑").getValue(),
                GanZhi.getByName("庚辰").getValue(), GanZhi.getByName("辛巳").getValue(), GanZhi.getByName("壬申").getValue(),
                GanZhi.getByName("癸亥").getValue()).contains(date.getGanZhiDay().getValue());
    }

    /**
     * 杨公忌日
     * 正月十三、二月十一、三月初九、四月初七、五月初五、
     * 六月初三、七月初一、七月廿九、八月廿七、九月廿五、
     * 十月廿三、冬月廿一、腊月十九
     * 为杨公十三忌日
     * @param date  要测试的日期
     * @return 是否为杨公忌日
     */
    public static boolean isMrYangTabooDay(LocalDate  date) {
        return isMrYangTabooDay(LunarDate.of(date));
    }
    public static boolean isMrYangTabooDay(LunarDate date) {
        int month = date.getMonth();
        int day   = date.getDay  ();
        return  (month ==  1 && day == 13) ||
                (month ==  2 && day == 11) ||
                (month ==  3 && day ==  9) ||
                (month ==  4 && day ==  7) ||
                (month ==  5 && day ==  5) ||
                (month ==  6 && day ==  3) ||
                (month ==  7 && day ==  1) ||
                (month ==  7 && day == 29) ||
                (month ==  8 && day == 27) ||
                (month ==  9 && day == 25) ||
                (month == 10 && day == 23) ||
                (month == 11 && day == 21) ||
                (month == 12 && day == 19);
    }

    /**
     * 重丧日
     * 正月庚日、二月辛日、三月戊日、四月丙日、
     * 五月丁日、六月己日、七月甲日、八月乙日、
     * 九月戊日、十月壬日、冬月癸日、腊月己日、
     * 为重丧日，重丧日如果有人死，必还会有另一个人一起死
     *
     * 四季重丧日
     * 寅午戌月 逢 未日
     * 亥卯未月 逢 辰日
     * 申子辰月 逢 丑日
     * 巳酉丑月 逢 戌日
     *
     * 走脚重丧日
     *
     * @param date  要测试的日期
     * @return 是否为重丧日
     */
    public static boolean isDoubleFuneralDay(LocalDate  date) {
        return isDoubleFuneralDay(GanZhiDate.of(date));
    }
    public static boolean isDoubleFuneralDay(GanZhiDate date) {
        int month = date.getGanZhiMonth().getZhi().getValue();
        int day   = date.getGanZhiDay  ().getGan().getValue();
        return  (month == Zhi.YIN .getValue() && day == Gan.GENG.getValue()) ||
                (month == Zhi.MAO .getValue() && day == Gan.XIN .getValue()) ||
                (month == Zhi.CHEN.getValue() && day == Gan.WU  .getValue()) ||
                (month == Zhi.SI  .getValue() && day == Gan.BING.getValue()) ||
                (month == Zhi.WU  .getValue() && day == Gan.DING.getValue()) ||
                (month == Zhi.WEI .getValue() && day == Gan.JI  .getValue()) ||
                (month == Zhi.SHEN.getValue() && day == Gan.JIA .getValue()) ||
                (month == Zhi.YOU .getValue() && day == Gan.YI  .getValue()) ||
                (month == Zhi.XU  .getValue() && day == Gan.WU  .getValue()) ||
                (month == Zhi.HAI .getValue() && day == Gan.REN .getValue()) ||
                (month == Zhi.ZI  .getValue() && day == Gan.GUI .getValue()) ||
                (month == Zhi.CHOU.getValue() && day == Gan.JI  .getValue());
    }

    /**
     * 探病忌日
     * 壬寅，壬午，庚午，甲寅，乙卯，己卯
     * 为探病忌日，探病忌日去探望病人，代病人亡故
     * @param date  要测试的日期
     * @return 是否为探病忌日
     */
    public static boolean isNoVisitSickDay(LocalDate  date) {
        return isNoVisitSickDay(GanZhiDate.of(date));
    }
    public static boolean isNoVisitSickDay(GanZhiDate date) {
        int day = date.getGanZhiDay().getValue();
        return  day == GanZhi.getByName("壬寅").getValue() ||
                day == GanZhi.getByName("壬午").getValue() ||
                day == GanZhi.getByName("庚午").getValue() ||
                day == GanZhi.getByName("甲寅").getValue() ||
                day == GanZhi.getByName("乙卯").getValue() ||
                day == GanZhi.getByName("己卯").getValue();
    }

    /**
     * 月忌日
     * 每月初五，十四，二十三为月忌日，诸事不宜
     * @param date  要测试的日期
     * @return 是否为月忌日
     */
    public static boolean isMonthlyTabooDay(LocalDate  date) {
        return isMonthlyTabooDay(LunarDate.of(date));
    }
    public static boolean isMonthlyTabooDay(LunarDate date) {
        int day = date.getDay();
        return day == 5 || day == 14 || day == 23;
    }
}
