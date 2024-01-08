package run.zhinan.zhouyi.classic.fate.energy;

import run.zhinan.zhouyi.classic.common.PositionType;
import run.zhinan.zhouyi.classic.fate.ColumnType;
import run.zhinan.zhouyi.classic.fate.energy.definition.EnergyScoreXingHeDefinition;

public interface EnergyScoreDefinition {
    int getScore(ColumnType column, PositionType position);

    static EnergyScoreDefinition getInstance() {
        return new EnergyScoreXingHeDefinition();
    }
}
