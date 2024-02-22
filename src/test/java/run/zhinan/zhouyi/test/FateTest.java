package run.zhinan.zhouyi.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import run.zhinan.zhouyi.classic.fate.*;
import run.zhinan.zhouyi.classic.fate.energy.EnergyType;
import run.zhinan.zhouyi.classic.fate.energy.GodEnergy;
import run.zhinan.zhouyi.classic.fate.energy.WuXingEnergy;
import run.zhinan.zhouyi.classic.fate.fortune.*;
import run.zhinan.zhouyi.common.WuXingEffect;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FateTest {
    private final static FateCode fateCode = FateCode.of(LocalDateTime.of(1976, 2, 11, 11, 40), 1);
    private final static LocalDateTime dateTime = LocalDateTime.of(2023, 11, 22, 15, 30);

    @Test
    public void testEnergyType() {
        Assert.assertEquals(EnergyType.TOO_STRONG, EnergyType.of(true , true ));
        Assert.assertEquals(EnergyType.STRONG    , EnergyType.of(true , false));
        Assert.assertEquals(EnergyType.WEAK      , EnergyType.of(false, false));
        Assert.assertEquals(EnergyType.TOO_WEAK  , EnergyType.of(false, true ));
    }

    @Test
    public void testPatternType() {
        Assert.assertEquals(FatePatternType.fatePatternTypes[0], FatePatternType.of(WuXingEffect.GIVE , PatternType.ADJUST));
        Assert.assertEquals(FatePatternType.fatePatternTypes[1], FatePatternType.of(WuXingEffect.HELP , PatternType.ADJUST));
        Assert.assertEquals(FatePatternType.fatePatternTypes[2], FatePatternType.of(WuXingEffect.LEAK , PatternType.ADJUST));
        Assert.assertEquals(FatePatternType.fatePatternTypes[3], FatePatternType.of(WuXingEffect.COST , PatternType.ADJUST));
        Assert.assertEquals(FatePatternType.fatePatternTypes[4], FatePatternType.of(WuXingEffect.CURB , PatternType.ADJUST));

        Assert.assertEquals(FatePatternType.fatePatternTypes[5], FatePatternType.of(WuXingEffect.GIVE , PatternType.FOLLOW));
        Assert.assertEquals(FatePatternType.fatePatternTypes[6], FatePatternType.of(WuXingEffect.HELP , PatternType.FOLLOW));
        Assert.assertEquals(FatePatternType.fatePatternTypes[7], FatePatternType.of(WuXingEffect.LEAK , PatternType.FOLLOW));
        Assert.assertEquals(FatePatternType.fatePatternTypes[8], FatePatternType.of(WuXingEffect.COST , PatternType.FOLLOW));
        Assert.assertEquals(FatePatternType.fatePatternTypes[9], FatePatternType.of(WuXingEffect.CURB , PatternType.FOLLOW));
    }

    @Test
    public void testFateCode() {
        System.out.println(JSON.toJSONString(fateCode,
                SerializerFeature.PrettyFormat, SerializerFeature.SortField, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteEnumUsingToString
        ));
    }

    @Test
    public void testFatePattern() {
        System.out.println(JSON.toJSONString(FatePattern.of(fateCode.getEnergy()),
                SerializerFeature.PrettyFormat, SerializerFeature.SortField, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteEnumUsingToString
        ));
    }

    @Test
    public void testDecadeFortune() {
        List<String> nameList = new ArrayList<>();
        DecadeFortune.list(fateCode).forEach(fortune -> nameList.add(fortune.toString()));
        System.out.println(JSON.toJSONString(nameList,
                SerializerFeature.PrettyFormat, SerializerFeature.SortField, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteEnumUsingToString
        ));
        Assert.assertEquals(DecadeFortune.of(dateTime, fateCode).getName(), "乙未");
    }

    @Test
    public void testYearFortune() {
        List<String> nameList = new ArrayList<>();
        YearFortune.list(DecadeFortune.of(dateTime, fateCode))
                .forEach(fortune -> nameList.add(fortune.toString()));
        System.out.println(JSON.toJSONString(nameList,
                SerializerFeature.PrettyFormat, SerializerFeature.SortField, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteEnumUsingToString
        ));
        Assert.assertEquals(YearFortune.of(dateTime, fateCode).getName(), "癸卯");
    }

    @Test
    public void testMonthFortune() {
        List<String> nameList = new ArrayList<>();
        MonthFortune.list(YearFortune.of(dateTime, fateCode)).forEach(fortune -> nameList.add(fortune.toString()));
        System.out.println(JSON.toJSONString(nameList,
                SerializerFeature.PrettyFormat, SerializerFeature.SortField, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteEnumUsingToString
        ));
        Assert.assertEquals(MonthFortune.of(dateTime, fateCode).getName(), "癸亥");
    }

    @Test
    public void testDailyFortune() {
        List<String> nameList = new ArrayList<>();
        DailyFortune.list(MonthFortune.of(dateTime, fateCode)).forEach(fortune -> nameList.add(fortune.toString()));
        System.out.println(JSON.toJSONString(nameList,
                SerializerFeature.PrettyFormat, SerializerFeature.SortField, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteEnumUsingToString
        ));
        Assert.assertEquals(DailyFortune.of(dateTime, fateCode).getName(), "甲申");
    }

    @Test
    public void testTimeFortune() {
        List<String> nameList = new ArrayList<>();
        TimeFortune.list(DailyFortune.of(dateTime, fateCode)).forEach(fortune -> nameList.add(fortune.toString()));
        System.out.println(JSON.toJSONString(nameList,
                SerializerFeature.PrettyFormat, SerializerFeature.SortField, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteEnumUsingToString
        ));
        Assert.assertEquals(TimeFortune.of(dateTime, fateCode).getName(), "壬申");
    }

    @Test
    public void testPeriodFortune() {
        List<String> nameList = new ArrayList<>();
        PeriodFortune.getFortunes(dateTime, fateCode, ColumnType.TIME_FORTUNE).forEach(fortune -> nameList.add(fortune.toString()));
        System.out.println(JSON.toJSONString(nameList,
                SerializerFeature.PrettyFormat, SerializerFeature.SortField, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteEnumUsingToString
        ));
        nameList.clear();
        PeriodFortune.getFortunes(dateTime, fateCode, ColumnType.DAILY_FORTUNE).forEach(fortune -> nameList.add(fortune.toString()));
        System.out.println(JSON.toJSONString(nameList,
                SerializerFeature.PrettyFormat, SerializerFeature.SortField, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteEnumUsingToString
        ));
    }

    @Test
    public void testGodEnergy() {
        System.out.println(JSON.toJSONString(GodEnergy.of(fateCode),
                SerializerFeature.PrettyFormat, SerializerFeature.SortField, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteEnumUsingToString
        ));
    }

    @Test
    public void testWuXingEnergy() {
        System.out.println(JSON.toJSONString(WuXingEnergy.of(fateCode, LocalDate.now()),
                SerializerFeature.PrettyFormat, SerializerFeature.SortField, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteEnumUsingToString
        ));
    }
}
