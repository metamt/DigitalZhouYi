package run.zhinan.zhouyi.classic.fate.energy;

import run.zhinan.zhouyi.classic.common.GanZhi;
import run.zhinan.zhouyi.classic.common.PositionType;
import run.zhinan.zhouyi.classic.fate.ColumnType;
import run.zhinan.zhouyi.classic.fate.FateCode;
import run.zhinan.zhouyi.classic.fate.FateCodeColumn;
import run.zhinan.zhouyi.classic.fate.fortune.PeriodFortune;
import run.zhinan.zhouyi.common.WuXing;

import java.time.LocalDate;
import java.util.*;

public class WuXingEnergy {
    private final static EnergyScoreDefinition definition = EnergyScoreDefinition.getInstance();

    FateCode fateCode;

    double total;

    Map<Integer, Integer> values      = new TreeMap<>();
    Map<Integer, Integer> number      = new TreeMap<>();
    Map<Integer, Double>  percentages = new TreeMap<>();

    public static WuXingEnergy of(FateCode fateCode) {
        return of(fateCode, null);
    }

    public static WuXingEnergy of(FateCode fateCode, LocalDate date) {
        List<FateCodeColumn> columnList = new ArrayList<>(Arrays.asList(fateCode.getFourColumns()));
        if (date != null) {
            columnList.addAll(PeriodFortune.getFortunes(date.atTime(12, 0), fateCode, ColumnType.DAILY_FORTUNE));
        }
        WuXingEnergy energy = of(columnList);
        energy.fateCode = fateCode;
        return energy;
    }

    public static WuXingEnergy of(List<FateCodeColumn> columnList) {
        WuXingEnergy energy = new WuXingEnergy();
        for (FateCodeColumn column : columnList) {
            energy.number.put(column.getGan().getWuXing().getValue(), energy.getNumber(column.getGan().getWuXing()) + 1);
            energy.number.put(column.getZhi().getWuXing().getValue(), energy.getNumber(column.getZhi().getWuXing()) + 1);

            for (PositionType position : PositionType.values()) {
                WuXing wuXing = getByPosition(column, position);
                energy.values.put(wuXing.getValue(), energy.getValue(wuXing) + WuXingEnergy.definition.getScore(column.getType(), position));
            }
        }

        energy.total = energy.getValue(WuXing.METAL)
                + energy.getValue(WuXing.WOOD) + energy.getValue(WuXing.WATER)
                + energy.getValue(WuXing.FIRE) + energy.getValue(WuXing.EARTH);

        for (WuXing wuXing : WuXing.values()) {
            energy.percentages.put(wuXing.getValue(), Math.floor(energy.getValue(wuXing) * 10000 / energy.total) / 100.0);
        }
        return energy;
    }

    private static WuXing getByPosition(GanZhi ganZhi, PositionType position) {
        WuXing result = null;
        switch (position) {
            case TIAN_GAN:
                result = ganZhi.getGan().getWuXing();
                break;
            case Di_ZHI:
                result = ganZhi.getZhi().getWuXing();
                break;
            case HIDDEN_GAN_2:
                result = ganZhi.getZhi().secondHiddenGan() == null ? ganZhi.getZhi().getWuXing() : ganZhi.getZhi().secondHiddenGan().getWuXing();
                break;
            case HIDDEN_GAN_3:
                result = ganZhi.getZhi().thirdHiddenGan() == null ?
                        ganZhi.getZhi().secondHiddenGan() == null ? ganZhi.getZhi().getWuXing() : ganZhi.getZhi().secondHiddenGan().getWuXing() :
                        ganZhi.getZhi().thirdHiddenGan().getWuXing();
        }
        return result;
    }

    public static FateCode getFateCode(WuXingEnergy energy) {
        return energy.fateCode;
    }

    public int getNumber(WuXing wuXing) {
        return number.computeIfAbsent(wuXing.getValue(), w -> 0);
    }

    public int getValue (WuXing wuXing) {
        return values.computeIfAbsent(wuXing.getValue(), w -> 0);
    }

    public double getPercentage(WuXing wuXing) {
        return percentages.computeIfAbsent(wuXing.getValue(), w -> 0.0);
    }

    public WuXing getMax() {
        Map<Integer, Integer> compareMap = new TreeMap<>();
        values.keySet().forEach(key -> compareMap.put(values.get(key), key));
        Set<Integer> keySet = compareMap.keySet();
        return WuXing.getByValue(compareMap.get(keySet.toArray(new Integer[0])[keySet.size() - 1]));
    }
}
