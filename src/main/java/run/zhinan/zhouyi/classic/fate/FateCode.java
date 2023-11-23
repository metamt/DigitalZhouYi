package run.zhinan.zhouyi.classic.fate;

import lombok.Getter;
import run.zhinan.time.ganzhi.GanZhiDateTime;
import run.zhinan.zhouyi.classic.common.Gan;
import run.zhinan.zhouyi.classic.common.GanZhi;
import run.zhinan.zhouyi.classic.common.Zhi;
import run.zhinan.zhouyi.classic.fate.energy.GodEnergy;
import run.zhinan.zhouyi.classic.fate.energy.WuXingEnergy;
import run.zhinan.zhouyi.common.Sex;

import java.time.LocalDateTime;

/**
 * @author withwind
 * 这里是原来的八字类，包含一个八字的所有信息
 * 阳历生日，阴历生日，干支信息
 * 四柱八字，地支藏干
 */
@Getter
public class FateCode {
    LocalDateTime birthday;
    Sex sex;

    FateCodeColumn[] fourColumns = new FateCodeColumn[4];

    WuXingEnergy energy;
    GodEnergy godEnergy;

    public static FateCode of(LocalDateTime birthday, int sex) {
        GanZhiDateTime ganZhiDateTime = GanZhiDateTime.of(birthday);
        FateCode fateCode = new FateCode();
        fateCode.birthday = birthday;
        fateCode.sex      = Sex.getByValue(sex);
        fateCode.fourColumns[0] = FateCodeColumn.of(GanZhi.of(ganZhiDateTime.getGanZhiYear ()), ColumnType.YEAR , fateCode);
        fateCode.fourColumns[1] = FateCodeColumn.of(GanZhi.of(ganZhiDateTime.getGanZhiMonth()), ColumnType.MONTH, fateCode);
        fateCode.fourColumns[2] = FateCodeColumn.of(GanZhi.of(ganZhiDateTime.getGanZhiDay  ()), ColumnType.DAY  , fateCode);
        fateCode.fourColumns[3] = FateCodeColumn.of(GanZhi.of(ganZhiDateTime.getGanZhiTime ()), ColumnType.TIME , fateCode);
        return fateCode;
    }

    /**
     * 通过序号拿到一柱干支
     * 0 - 年柱
     * 1 - 月柱
     * 2 - 日柱
     * 3 - 时柱
     * @param i 干支所处的序号
     * @return 对应序号的一柱干支
     */
    public FateCodeColumn getColumn(int i) {
        return fourColumns[i];
    }

    /**
     * 更具四柱类型拿到一柱干支
     * @param columnType 四柱类型
     * @return 对应的一柱干支
     */
    public GanZhi getColumn(ColumnType columnType) {
        return getColumn(columnType.getValue());
    }

    /**
     * 获得命主，命主为日干
     * @return 命主对应的天干
     */
    public Gan getFate() {
        return getColumn(ColumnType.DAY).getGan();
    }

    /**
     * 获取月令，月令为月支
     * @return 月令对应的日支
     */
    public Zhi getMaster() {
        return getColumn(ColumnType.MONTH).getZhi();
    }

    public WuXingEnergy getEnergy() {
        if (energy == null) energy = WuXingEnergy.of(this);
        return energy;
    }

    public GodEnergy getGodEnergy() {
        if (godEnergy == null) godEnergy = GodEnergy.of(this);
        return godEnergy;
    }
}
