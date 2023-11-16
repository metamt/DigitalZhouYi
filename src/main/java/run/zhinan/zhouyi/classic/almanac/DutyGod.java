package run.zhinan.zhouyi.classic.almanac;

import lombok.AllArgsConstructor;
import lombok.Getter;
import run.zhinan.time.ganzhi.Zhi;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public class DutyGod {
    public final static DutyGod BLUE_DRAGON   = new DutyGod( 0, "青龙", GoodLevel.GOOD, DutyWay.YELLOW);
    public final static DutyGod BRIGHT_YARD   = new DutyGod( 1, "明堂", GoodLevel.GOOD, DutyWay.YELLOW);
    public final static DutyGod HEAVEN_PUNISH = new DutyGod( 2, "天刑", GoodLevel.BAD , DutyWay.BLACK );
    public final static DutyGod ROSE_FINCH    = new DutyGod( 3, "朱雀", GoodLevel.BAD , DutyWay.BLACK );
    public final static DutyGod GOLD_CHAMBER  = new DutyGod( 4, "金匮", GoodLevel.GOOD, DutyWay.YELLOW);
    public final static DutyGod HEAVEN_VIRTUE = new DutyGod( 5, "天德", GoodLevel.GOOD, DutyWay.YELLOW);
    public final static DutyGod WHITE_TIGER   = new DutyGod( 6, "白虎", GoodLevel.BAD , DutyWay.BLACK );
    public final static DutyGod JADE_YARD     = new DutyGod( 7, "玉堂", GoodLevel.GOOD, DutyWay.YELLOW);
    public final static DutyGod HEAVEN_PRISON = new DutyGod( 8, "天牢", GoodLevel.BAD , DutyWay.BLACK );
    public final static DutyGod TURTLE_SNAKE  = new DutyGod( 9, "玄武", GoodLevel.BAD , DutyWay.BLACK );
    public final static DutyGod FATE_MANAGER  = new DutyGod(10, "司命", GoodLevel.GOOD, DutyWay.YELLOW);
    public final static DutyGod KYLIN_UNICORN = new DutyGod(11, "勾陈", GoodLevel.BAD , DutyWay.BLACK );

    public final static DutyGod[] values = {
            BLUE_DRAGON, BRIGHT_YARD, HEAVEN_PUNISH, ROSE_FINCH,
            GOLD_CHAMBER, HEAVEN_VIRTUE, WHITE_TIGER, JADE_YARD,
            HEAVEN_PRISON, TURTLE_SNAKE, FATE_MANAGER, KYLIN_UNICORN};
    int value;
    String name;
    GoodLevel goodLevel;
    DutyWay dutyWay;

    @Override
    public String toString() {
        return name;
    }

    public static DutyGod[] of(Zhi zhi) {
        DutyGod[] dutyGods = new DutyGod[12];
        for (int i = 0; i < 12; i++) {
            dutyGods[i] = values[(i + 4 - (zhi.getValue() - 1) * 2 + 24) % 12];
        }
        return dutyGods;
    }

    public static DutyGod of(Zhi z1, Zhi z2) {
        return of(z1)[z2.getValue() - 1];
    }

    public static void main(String[] args) {
        for (Zhi zhi : Zhi.values()) {
            System.out.println(Arrays.deepToString(of(zhi)));
        }
    }
}
