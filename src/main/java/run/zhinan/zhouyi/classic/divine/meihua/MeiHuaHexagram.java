package run.zhinan.zhouyi.classic.divine.meihua;

import lombok.Getter;
import run.zhinan.zhouyi.classic.divine.common.BasicHexagram;
import run.zhinan.zhouyi.classic.divine.common.CompositeHexagram;
import run.zhinan.zhouyi.classic.fate.fortune.FortuneGoodLevel;
import run.zhinan.zhouyi.common.WuXing;
import run.zhinan.zhouyi.common.WuXingEffect;
import run.zhinan.zhouyi.common.WuXingStatus;

@Getter
public class MeiHuaHexagram {
    CompositeHexagram hexagram;
    int change;
    WuXing weather;
    WuXingEffect effect;
    WuXingStatus selfStatus;
    WuXingStatus changeStatus;
    GoodLevel goodLevel;

    public MeiHuaHexagram(CompositeHexagram hexagram, int change, WuXing weather) {
        this.hexagram     = hexagram;
        this.weather      = weather;
        this.change       = change;
        this.effect       = WuXingEffect.of(getSelf().getWuXing(), getChange().getWuXing());
        this.goodLevel    = GoodLevel.getByEffect(this.effect);
        this.selfStatus   = WuXingStatus.of(getSelf().getWuXing(), getWeather());
        this.changeStatus = WuXingStatus.of(getChange().getWuXing(), getWeather());
    }

    public BasicHexagram getSelf() {
        return change > 3 ? hexagram.getUnder() : hexagram.getUpper();
    }

    public BasicHexagram getChange() {
        return change > 3 ? hexagram.getUpper() : hexagram.getUnder();
    }
}
