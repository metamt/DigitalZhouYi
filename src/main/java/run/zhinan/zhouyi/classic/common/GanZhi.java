package run.zhinan.zhouyi.classic.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import run.zhinan.zhouyi.classic.fate.FateSound;

@Getter
@RequiredArgsConstructor
public class GanZhi {
    final Gan gan;
    final Zhi zhi;

    public static GanZhi of(run.zhinan.time.ganzhi.GanZhi ganZhi) {
        return new GanZhi(Gan.getByValue(ganZhi.getGan().getValue()), Zhi.getByValue(ganZhi.getZhi().getValue()));
    }

    public static GanZhi of(int ganValue, int zhiValue) {
        return new GanZhi(Gan.getByValue(ganValue), Zhi.getByValue(zhiValue));
    }

    public static GanZhi getByName(String name) {
        GanZhi ganZhi = null;
        if (name.length() == 2) {
            ganZhi = new GanZhi(Gan.getByName(name.substring(0, 1)), Zhi.getByName(name.substring(1)));
        }
        return ganZhi;
    }

    public int getValue() {
        return ((6 - ((zhi.getValue() + 12 - gan.getValue()) % 12) / 2) % 6) * 10 + gan.getValue();
    }

    public String getName() {
        return gan.getName() + zhi.getName();
    }

    public FateSound getSound() {
        return FateSound.of(this);
    }

    public Zhi[] getEmpty() {
        int e = (10 - gan.getValue() + zhi.getValue()) % 12;
        return new Zhi[] {Zhi.getByValue(e + 1), Zhi.getByValue((e + 1) % 12 + 1)};
    }
}
