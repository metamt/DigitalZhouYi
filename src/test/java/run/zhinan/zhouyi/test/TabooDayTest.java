package run.zhinan.zhouyi.test;

import org.junit.jupiter.api.Test;
import org.testng.Assert;
import run.zhinan.zhouyi.classic.almanac.TabooDay;

import java.time.LocalDate;

public class TabooDayTest {
    @Test
    public void testFourDetachDay() {
        Assert.assertTrue(TabooDay.isFourDetachDay(LocalDate.of(2023,  3, 20)));
        Assert.assertTrue(TabooDay.isFourDetachDay(LocalDate.of(2023,  6, 20)));
        Assert.assertTrue(TabooDay.isFourDetachDay(LocalDate.of(2023,  9, 22)));
        Assert.assertTrue(TabooDay.isFourDetachDay(LocalDate.of(2023, 12, 21)));
        System.out.println("四离日测试通过！！！");
    }

    @Test
    public void testFourExtinctionDay() {
        Assert.assertTrue(TabooDay.isFourExtinctionDay(LocalDate.of(2023,  2, 3)));
        Assert.assertTrue(TabooDay.isFourExtinctionDay(LocalDate.of(2023,  5, 5)));
        Assert.assertTrue(TabooDay.isFourExtinctionDay(LocalDate.of(2023,  8, 7)));
        Assert.assertTrue(TabooDay.isFourExtinctionDay(LocalDate.of(2023, 11, 7)));
        System.out.println("四绝日测试通过！！！");
    }

    @Test
    public void testFourUselessDay() {
    }

    @Test
    public void testRedEvilDay() {
    }

    @Test
    public void testYellowEvilDay() {
    }

    @Test
    public void testNoCookDay() {
    }

    @Test
    public void testTenVeryBadDay() {
    }

    @Test
    public void testMrYangTabooDay() {
        Assert.assertTrue(TabooDay.isMrYangTabooDay(LocalDate.of(2023,  2,  3)));
        Assert.assertTrue(TabooDay.isMrYangTabooDay(LocalDate.of(2023,  3,  2)));
        Assert.assertTrue(TabooDay.isMrYangTabooDay(LocalDate.of(2023,  4,  1)));
        Assert.assertTrue(TabooDay.isMrYangTabooDay(LocalDate.of(2023,  4, 28)));
        Assert.assertTrue(TabooDay.isMrYangTabooDay(LocalDate.of(2023,  5, 25)));
        Assert.assertTrue(TabooDay.isMrYangTabooDay(LocalDate.of(2023,  6, 22)));
        Assert.assertTrue(TabooDay.isMrYangTabooDay(LocalDate.of(2023,  7, 20)));
        Assert.assertTrue(TabooDay.isMrYangTabooDay(LocalDate.of(2023,  8, 16)));
        Assert.assertTrue(TabooDay.isMrYangTabooDay(LocalDate.of(2023,  9, 13)));
        Assert.assertTrue(TabooDay.isMrYangTabooDay(LocalDate.of(2023, 10, 11)));
        Assert.assertTrue(TabooDay.isMrYangTabooDay(LocalDate.of(2023, 11,  8)));
        Assert.assertTrue(TabooDay.isMrYangTabooDay(LocalDate.of(2023, 12,  5)));
        Assert.assertTrue(TabooDay.isMrYangTabooDay(LocalDate.of(2024,  1,  2)));
        Assert.assertTrue(TabooDay.isMrYangTabooDay(LocalDate.of(2024,  1, 29)));
        System.out.println("杨公忌日测试通过！！！");
    }

    @Test
    public void testDoubleFuneralDay() {
    }

    @Test
    public void testNoVisitSickDay() {
        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023,  2, 13)));
        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023,  2, 25)));
        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023,  2, 26)));

        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023,  3, 13)));
        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023,  3, 22)));
        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023,  3, 25)));

        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023,  4, 14)));
        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023,  4, 26)));
        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023,  4, 27)));

        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023,  5, 12)));
        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023,  5, 21)));
        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023,  5, 24)));

        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023,  6, 13)));
        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023,  6, 25)));
        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023,  6, 26)));

        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023,  7, 11)));
        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023,  7, 20)));
        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023,  7, 23)));

        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023,  8, 12)));
        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023,  8, 24)));
        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023,  8, 25)));

        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023,  9,  9)));
        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023,  9, 18)));
        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023,  9, 21)));

        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023, 10, 11)));
        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023, 10, 23)));
        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023, 10, 24)));

        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023, 11,  8)));
        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023, 11, 17)));
        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023, 11, 20)));

        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023, 12, 10)));
        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2023, 12, 22)));

        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2024,  1,  7)));
        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2024,  1, 16)));
        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2024,  1, 19)));

        Assert.assertTrue(TabooDay.isNoVisitSickDay(LocalDate.of(2024,  2,  8)));
        System.out.println("探病忌日测试通过！！！");
    }

    @Test
    public void testMonthlyTabooDay() {
        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023,  1, 26)));

        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023,  2,  4)));
        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023,  2, 13)));
        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023,  2, 24)));

        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023,  3,  5)));
        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023,  3, 14)));
        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023,  3, 26)));

        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023,  4,  4)));
        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023,  4, 13)));
        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023,  4, 24)));

        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023,  5,  3)));
        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023,  5, 12)));
        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023,  5, 23)));

        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023,  6,  1)));
        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023,  6, 10)));
        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023,  6, 22)));

        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023,  7,  1)));
        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023,  7, 10)));
        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023,  7, 22)));
        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023,  7, 31)));

        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023,  8,  9)));
        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023,  8, 20)));
        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023,  8, 29)));

        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023,  9,  7)));
        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023,  9, 19)));
        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023,  9, 28)));

        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023, 10,  7)));
        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023, 10, 19)));
        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023, 10, 28)));

        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023, 11,  6)));
        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023, 11, 17)));
        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023, 11, 26)));

        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023, 12,  5)));
        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023, 12, 17)));
        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2023, 12, 26)));

        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2024,  1,  4)));
        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2024,  1, 15)));
        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2024,  1, 24)));

        Assert.assertTrue(TabooDay.isMonthlyTabooDay(LocalDate.of(2024,  2,  2)));
        System.out.println("月忌日测试通过！！！");
    }

    @Test
    public void testGoodDay() {
        Assert.assertFalse(TabooDay.isBadDay(LocalDate.of(2023,  1,  22)));
    }
}
