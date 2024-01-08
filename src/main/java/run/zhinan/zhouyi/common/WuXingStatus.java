package run.zhinan.zhouyi.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WuXingStatus {
    Flourish ("旺", 100, true ),
    Stable   ("相",  80, true ),
    Rest     ("休",  50, false),
    Constrain("囚",  30, false),
    Died     ("死",   0, false);

    String name;
    int power;
    boolean good;

    private static final WuXingStatus[] values = new WuXingStatus[] {
            Rest, Flourish, Stable, Died, Constrain
    };

    public static WuXingStatus of(WuXing self, WuXing weather) {
        return values[WuXingEffect.of(weather, self).getValue()];
    }
}
