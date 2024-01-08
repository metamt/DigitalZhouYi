package run.zhinan.zhouyi.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import run.zhinan.zhouyi.classic.fate.ColumnType;
import run.zhinan.zhouyi.classic.fate.FateCode;
import run.zhinan.zhouyi.classic.fate.FatePattern;
import run.zhinan.zhouyi.classic.fate.energy.GodEnergy;
import run.zhinan.zhouyi.classic.fate.energy.WuXingEnergy;
import run.zhinan.zhouyi.classic.fate.fortune.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FateTest {
    private final static FateCode fateCode = FateCode.of(LocalDateTime.of(1976, 2, 11, 11, 40), 1);
    private final static LocalDateTime dateTime = LocalDateTime.of(2023, 11, 22, 15, 30);

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
