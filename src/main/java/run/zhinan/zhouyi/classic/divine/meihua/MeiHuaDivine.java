package run.zhinan.zhouyi.classic.divine.meihua;

import lombok.Getter;
import run.zhinan.time.ganzhi.GanZhiDate;
import run.zhinan.zhouyi.classic.common.GanZhi;
import run.zhinan.zhouyi.classic.divine.common.BaseDivine;
import run.zhinan.zhouyi.classic.divine.common.CompositeHexagram;
import run.zhinan.zhouyi.classic.divine.common.HexagramTools;
import run.zhinan.zhouyi.common.WuXing;
import run.zhinan.zhouyi.util.ChineseUtil;

import java.time.LocalDateTime;

@Getter
public class MeiHuaDivine extends BaseDivine {
    Integer number1;
    Integer number2;
    Integer change;

    WuXing weather;

    MeiHuaHexagram origin;
    MeiHuaHexagram process;
    MeiHuaHexagram result;

    public MeiHuaDivine(String question, LocalDateTime divineTime, Integer number1, Integer number2, Integer change) {
        super(question, divineTime == null? LocalDateTime.now() : divineTime);
        this.number1 = number1 == null ? calculateNumber1ForTime() : calculateNumberForChinese(number1);
        this.number2 = number2 == null ? calculateNumber2ForTime() : calculateNumberForChinese(number2);
        this.change  = change  == null ? calculateChangeForTime () : change;
        this.weather = GanZhi.of(GanZhiDate.of(this.getDivineTime().toLocalDate()).getGanZhiMonth()).getZhi().getWuXing();

        addContext("number1", String.valueOf(this.number1));
        addContext("number2", String.valueOf(this.number2));
        addContext("change" , String.valueOf(this.change ));

        CompositeHexagram o = CompositeHexagram.getByUpperAndUnder((this.number1 + 7) % 8 + 1, (this.number2 + 7) % 8 + 1);
        CompositeHexagram p = HexagramTools.toNuclearHexagram(o);
        CompositeHexagram r = o.change(this.change);


        this.origin  = new MeiHuaHexagram(o, this.change, weather);
        this.process = new MeiHuaHexagram(p, this.change, weather);
        this.result  = new MeiHuaHexagram(r, this.change, weather);
    }

    public Integer calculateNumber1ForTime() {
        return getDivineTime().getYear() + getDivineTime().getMonthValue() + getDivineTime().getDayOfMonth();
    }

    public Integer calculateNumber2ForTime() {
        return getDivineTime().getYear() + getDivineTime().getMonthValue() + getDivineTime().getDayOfMonth() + getDivineTime().getHour();
    }

    public Integer calculateChangeForTime() {
        return (number1 + number2 + getDivineTime().getHour() + 5) % 6 + 1;
    }

    public static Integer calculateNumberForChinese(Integer number) {
        return ChineseUtil.isChineseCharacter((char) number.intValue()) ?
                ChineseUtil.calculateNumberFromChineseCharacter((char) number.intValue()) : number;
    }

    public static MeiHuaDivine init(String question, LocalDateTime divineTime, Integer number1, Integer number2, Integer change) {
        return new MeiHuaDivine(question, divineTime, number1, number2, change);
    }

}
