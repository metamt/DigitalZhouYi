package run.zhinan.zhouyi.classic.fate.energy;

import run.zhinan.zhouyi.classic.common.PositionType;
import run.zhinan.zhouyi.classic.fate.ColumnType;

public interface EnergyScoreDefinition {
    int getScore(ColumnType column, PositionType position);
}
