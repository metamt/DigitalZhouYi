package run.zhinan.zhouyi.classic.divine.liuyao;

import lombok.Getter;
import run.zhinan.time.ganzhi.GanZhiDateTime;
import run.zhinan.time.solar.SolarTerm;
import run.zhinan.zhouyi.classic.common.Gan;
import run.zhinan.zhouyi.classic.common.GanZhi;
import run.zhinan.zhouyi.classic.common.Zhi;
import run.zhinan.zhouyi.classic.divine.common.BaseDivine;
import run.zhinan.zhouyi.common.Sex;

import java.time.LocalDateTime;

@Getter
public class LiuYaoDivine extends BaseDivine {
    int[] data;
    GanZhiDateTime ganZhiDateTime;

    SolarTerm lastSolarTerm;
    SolarTerm nextSolarTerm;

    Zhi[] dayEmpty;

    String[]       gods = new String[6];
    LiuYaoHexagram origin;
    LiuYaoHexagram result;

    public LiuYaoDivine(String question, LocalDateTime divineTime, int[] data) {
        super(question, divineTime);
        addContext("data", LiuYaoHexagram.buildCode(data));

        this.data           = data;
        this.ganZhiDateTime = GanZhiDateTime.of(divineTime);
        this.lastSolarTerm  = SolarTerm.getLastMajorSolarTerm(divineTime);
        this.nextSolarTerm  = SolarTerm.getNextMajorSolarTerm(divineTime);
        this.dayEmpty       = GanZhi.of(this.ganZhiDateTime.getGanZhiDay()).getEmpty();

        int[] changeData = new int[6];
        Gan fate = GanZhi.of(ganZhiDateTime.getGanZhiDay()).getGan();
        for (int i = 0; i < 6; i++) {
            this.gods [i] = HexagramGod.getByValue((i + (fate.getValue() < 5 ? fate.getValue() / 2 : (fate.getValue() + 2) / 2)) % 6).getName();
            changeData[i] = change(data[i]);
        }

        this.origin = LiuYaoHexagram.of(data);
        this.result = LiuYaoHexagram.of(changeData);
    }

    public int change(int d) {
        int r = d;
        if (d == 6) r = 7;
        if (d == 9) r = 8;
        return r;
    }

    public static LiuYaoDivine of(String question, QuestionType questionType, LocalDateTime divineTime, int sex, int[] data) {
        LiuYaoDivine divine = new LiuYaoDivine(question, divineTime, data);
        divine.addContext("type", questionType.getName());
        divine.addContext("sex" , Sex.getByValue(sex).getName());
        return divine;
    }
}
