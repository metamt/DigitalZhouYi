package run.zhinan.zhouyi.classic.fate.energy;

import lombok.Getter;
import run.zhinan.zhouyi.classic.common.GanZhi;
import run.zhinan.zhouyi.classic.common.PositionType;
import run.zhinan.zhouyi.classic.fate.ColumnType;
import run.zhinan.zhouyi.classic.fate.FateCode;
import run.zhinan.zhouyi.classic.fate.energy.definition.EnergyScoreXingHeDefinition;
import run.zhinan.zhouyi.common.WuXing;

import java.util.*;

@Getter
public class WuXingEnergy {
    private final static EnergyScoreDefinition definition = EnergyScoreDefinition.getInstance();

    double total;

    Map<Integer, Integer> values      = new TreeMap<>();
    Map<Integer, Integer> number      = new TreeMap<>();
    Map<Integer, Double>  percentages = new TreeMap<>();

    public static WuXingEnergy of(FateCode bazi) {
        WuXingEnergy energy = new WuXingEnergy();
        for (ColumnType column : ColumnType.originals) {
            GanZhi ganZhi = bazi.getColumn(column);

            energy.number.put(ganZhi.getGan().getWuXing().getValue(), energy.getNumber(ganZhi.getGan().getWuXing()) + 1);
            energy.number.put(ganZhi.getZhi().getWuXing().getValue(), energy.getNumber(ganZhi.getZhi().getWuXing()) + 1);

            for (PositionType position : PositionType.values()) {
                WuXing wuXing = getByPosition(ganZhi, position);
                energy.values.put(wuXing.getValue(), energy.getValue(wuXing) + WuXingEnergy.definition.getScore(column, position));
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
                result = ganZhi.getZhi().getSecondHiddenGan() == null ? ganZhi.getZhi().getWuXing() : ganZhi.getZhi().getSecondHiddenGan().getWuXing();
                break;
            case HIDDEN_GAN_3:
                result = ganZhi.getZhi().getThirdHiddenGan() == null ?
                        ganZhi.getZhi().getSecondHiddenGan() == null ? ganZhi.getZhi().getWuXing() : ganZhi.getZhi().getSecondHiddenGan().getWuXing() :
                        ganZhi.getZhi().getThirdHiddenGan ().getWuXing();
        }
        return result;
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
