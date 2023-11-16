package run.zhinan.zhouyi.classic.almanac;

import lombok.AllArgsConstructor;
import lombok.Getter;
import run.zhinan.time.ganzhi.GanZhiDate;

@Getter
@AllArgsConstructor
public class DutyStar {
    public final static DutyStar BUILD    = new DutyStar( 0, "建", DutyWay.BLACK , GoodLevel.OK  );    // 建日：建基立业，从头开始，如苗初长，终获收成。
    public final static DutyStar REMOVE   = new DutyStar( 1, "除", DutyWay.YELLOW, GoodLevel.OK  );    // 除日：除旧迎新，沐浴洗礼，拜神祈福，安居乐业。
    public final static DutyStar FULL     = new DutyStar( 2, "满", DutyWay.BLACK , GoodLevel.OK  );    // 满日：满载而归，圆圆满满，兴高采烈，得胜之时。
    public final static DutyStar FLAT     = new DutyStar( 3, "平", DutyWay.BLACK , GoodLevel.OK  );    // 平日：平平安安，平平常常，不平不忿，吉凶各半。
    public final static DutyStar DECIDE   = new DutyStar( 4, "定", DutyWay.YELLOW, GoodLevel.OK  );    // 定日：定如泰山，安定团结，事已成形，根基牢固。
    public final static DutyStar HOLD     = new DutyStar( 5, "执", DutyWay.YELLOW, GoodLevel.BAD );    // 执日：执日多忧，顽固不化，吉凶各半，不可大用。
    public final static DutyStar BREAK    = new DutyStar( 6, "破", DutyWay.BLACK , GoodLevel.BAD );    // 破日：月建冲破，最喜动工，红白喜事，十用九凶。
    public final static DutyStar DANGER   = new DutyStar( 7, "危", DutyWay.YELLOW, GoodLevel.BAD );    // 危日：危日惊险，做事小心，岌岌可危，凶煞占宫。
    public final static DutyStar DONE     = new DutyStar( 8, "成", DutyWay.YELLOW, GoodLevel.GOOD);    // 成日：吉星高照，贵人接引，天皇地皇，金玉满堂。
    public final static DutyStar RECOUP   = new DutyStar( 9, "收", DutyWay.BLACK , GoodLevel.GOOD);    // 收日：煞集中宫，只宜回笼，收账要债，此日最佳。
    public final static DutyStar OPEN     = new DutyStar(10, "开", DutyWay.YELLOW, GoodLevel.GOOD);    // 开日：马到成功，旗开得胜，大吉大利，百无禁忌。
    public final static DutyStar CLOSE    = new DutyStar(11, "闭", DutyWay.BLACK , GoodLevel.BAD );    // 闭日：闭门不出，闭塞不通，万物归仓，只宜静养。

    public final static DutyStar[] values = {BUILD, REMOVE, FULL, FLAT, DECIDE, HOLD, BREAK, DANGER, DONE, RECOUP, OPEN, CLOSE};

    int value;
    String name;
    DutyWay dutyWay;
    GoodLevel goodLevel;

    public String[] getGoodFor() {
        return goodFor[getValue()];
    }
    public String[]  getBadFor() { return badFor [getValue()]; }

    public static DutyStar[] values() { return values; }

    public static DutyStar of(GanZhiDate date) {
        return values[(date.getGanZhiDay().getZhi().getValue() + 12 - date.getGanZhiMonth().getZhi().getValue()) % 12];
    }

    private final static String[][] goodFor = {
            {"赴任", "祈福", "求嗣", "破土", "安葬", "修造", "上梁", "求财", "置业", "入学", "考试", "结婚", "动土", "签约", "交涉", "出行"},  // 建
            {"祭祀", "祈福", "婚姻", "出行", "入伙", "搬迁", "出货", "动土", "求医", "交易"},                                              // 除
            {"嫁娶", "祈福", "移徙", "开市", "交易", "求财", "立契", "祭祀", "出行", "牧养"},                                              // 满

            {"嫁娶", "修造", "破土", "安葬", "牧养", "开市", "安床", "动土", "求嗣"},                                                     // 平
            {"祭祀", "祈福", "嫁娶", "造屋", "装修", "修路", "开市", "入学", "上任", "入伙"},                                              // 定

            {"造屋", "装修", "嫁娶", "收购", "立契", "祭祀"},                                                                           // 执
            {"破土", "拆卸", "求医"},                                                                                                 // 破
            {"祭祀", "祈福", "安床", "拆卸", "破土"},                                                                                  // 危

            {"结婚", "开市", "修造", "动土", "安床", "破土", "安葬", "搬迁", "交易", "求财", "出行", "立契", "竖柱", "裁种", "牧养"},         // 成
            {"祈福", "求嗣", "赴任", "嫁娶", "安床", "修造", "动土", "求学", "开市", "交易", "买卖", "立契"},                               // 收
            {"祭祀", "祈福", "入学", "上任", "修造", "动土", "开市", "安床", "交易", "出行", "竖柱"},                                      // 开

            {"祭祀", "祈福", "筑堤", "埋池", "埋穴", "造葬", "填补", "修屋"},                                                            // 闭
    };
    private final static String[][] badFor  = {
            {"动土", "开仓", "掘井", "乘船", "新船下水", "新车下地", "维修"},                                                             // 建
            {"结婚", "赴任", "远行", "签约"},                                                                                          // 除
            {"造葬", "赴任", "求医"},                                                                                                 // 满

            {"祈福", "求嗣", "赴任", "嫁娶", "开市", "安葬"},                                                                           // 平
            {"开市", "求财", "出行", "搬迁"},                                                                                          // 定

            {"诉讼", "出行", "交涉"},                                                                                                 // 执
            {"嫁娶", "签约", "交涉", "出行", "搬迁"},                                                                                  // 破
            {"登山", "乘船", "出行", "嫁娶", "造葬", "迁徙"},                                                                           // 危

            {"诉讼"},                                                                                                                // 成
            {"放债", "新船下水", "新车下地", "破土", "安葬"},                                                                            // 收
            {"放债", "诉讼", "安葬"},                                                                                                 // 开

            {"开市", "出行", "求医", "手术", "嫁娶"},                                                                                  // 闭
    };
}
