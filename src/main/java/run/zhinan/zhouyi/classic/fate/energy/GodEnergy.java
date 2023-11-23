package run.zhinan.zhouyi.classic.fate.energy;

import run.zhinan.zhouyi.classic.common.Gan;
import run.zhinan.zhouyi.classic.fate.FateCode;
import run.zhinan.zhouyi.classic.fate.FateCodeColumn;
import run.zhinan.zhouyi.classic.fate.FateGod;

public class GodEnergy {
    int[] ganValues = new int[10];
    int[] zhiValues = new int[10];

    public static GodEnergy of(FateCode fateCode) {
        return of(fateCode.getFourColumns(), fateCode.getFate());
    }

    public static GodEnergy of(FateCodeColumn[] columns, Gan fate) {
        GodEnergy godEnergy = new GodEnergy();
        for (int i = 0; i < columns.length; i++) {
            FateCodeColumn ganZhi = columns[i];
            if (ganZhi != null) {
                if (i != 2)
                    godEnergy.ganValues[fate.effect(ganZhi.getGan()).getValue()] += 40;
                Gan[] hiddenGans = ganZhi.getZhi().getHiddenGans();
                godEnergy.zhiValues[fate.effect(hiddenGans[0]).getValue()] += (hiddenGans.length > 1 ? 70 : 100) * (i == 1 ? 1.5 : 1);
                if (hiddenGans.length > 1)
                    godEnergy.zhiValues[fate.effect(hiddenGans[1]).getValue()] += (hiddenGans.length > 2 ? 20 : 30) * (i == 1 ? 1.5 : 1);
                if (hiddenGans.length > 2)
                    godEnergy.zhiValues[fate.effect(hiddenGans[2]).getValue()] += 10 * (i == 1 ? 1.5 : 1);
            }
        }
        return godEnergy;
    }

    public int getValue(FateGod god) {
        return getGanValue(god) + getZhiValue(god);
    }

    public int getGanValue(FateGod god) {
        return ganValues[god.getValue()];
    }

    public int getZhiValue(FateGod god) {
        return zhiValues[god.getValue()];
    }
}