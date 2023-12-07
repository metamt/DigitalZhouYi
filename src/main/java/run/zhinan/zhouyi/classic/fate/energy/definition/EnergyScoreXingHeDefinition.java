package run.zhinan.zhouyi.classic.fate.energy.definition;

import run.zhinan.zhouyi.classic.common.PositionType;
import run.zhinan.zhouyi.classic.fate.ColumnType;
import run.zhinan.zhouyi.classic.fate.energy.EnergyScoreDefinition;

public class EnergyScoreXingHeDefinition implements EnergyScoreDefinition {
    private final static int[] scores = {
            40,  70, 20, 10,    // 年柱
            40, 105, 30, 15,    // 月柱
            40,  70, 20, 10,    // 日柱
            40,  70, 20, 10,    // 时柱

            40,  70, 20, 10,    // 大运
            40,  70, 20, 10,    // 年运
            40,  70, 20, 10,    // 月运
            40,  70, 20, 10,    // 日运
            40,  70, 20, 10,    // 时运
    };

    public int getScore(ColumnType column, PositionType position) {
        return scores[column.getValue() * 4 + position.getValue()];
    }
}
