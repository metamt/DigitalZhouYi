package run.zhinan.zhouyi.classic.fate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import run.zhinan.zhouyi.common.WuXingEffect;

import static run.zhinan.zhouyi.classic.fate.PatternType.ADJUST;
import static run.zhinan.zhouyi.classic.fate.PatternType.FOLLOW;
import static run.zhinan.zhouyi.common.WuXingEffect.*;

@Getter
@AllArgsConstructor
public class FatePatternType {
    int value;
    String name;

    WuXingEffect master;
    PatternType  patternType;

    WuXingEffect[] goodEffects;
    WuXingEffect[]  badEffects;

    private final static FatePatternType[] fatePatternTypes = {
            new FatePatternType( 0, "印枭主导的偏旺格", GIVE, ADJUST, new WuXingEffect[] {COST, LEAK, CURB}, new WuXingEffect[] {GIVE, HELP}),
            new FatePatternType( 1, "截比主导的偏旺格", HELP, ADJUST, new WuXingEffect[] {LEAK, COST, CURB}, new WuXingEffect[] {HELP, GIVE}),
            new FatePatternType( 2, "食伤主导的偏弱格", LEAK, ADJUST, new WuXingEffect[] {GIVE, HELP}, new WuXingEffect[] {LEAK, CURB, COST}),
            new FatePatternType( 3, "财才主导的偏弱格", COST, ADJUST, new WuXingEffect[] {HELP, GIVE}, new WuXingEffect[] {COST, LEAK, CURB}),
            new FatePatternType( 4, "官杀主导的偏弱格", CURB, ADJUST, new WuXingEffect[] {GIVE, HELP}, new WuXingEffect[] {CURB, COST, LEAK}),

            new FatePatternType(10, "印枭主导的从旺格", GIVE, FOLLOW, new WuXingEffect[] {GIVE, HELP}, new WuXingEffect[] {COST, LEAK, CURB}),
            new FatePatternType(11, "截比主导的从旺格", HELP, FOLLOW, new WuXingEffect[] {HELP, GIVE}, new WuXingEffect[] {LEAK, COST, CURB}),
            new FatePatternType(12, "食伤主导的从弱格", LEAK, FOLLOW, new WuXingEffect[] {LEAK, CURB, COST}, new WuXingEffect[] {GIVE, HELP}),
            new FatePatternType(13, "财才主导的从弱格", COST, FOLLOW, new WuXingEffect[] {COST, LEAK, CURB}, new WuXingEffect[] {HELP, GIVE}),
            new FatePatternType(14, "官杀主导的从弱格", CURB, FOLLOW, new WuXingEffect[] {CURB, COST, LEAK}, new WuXingEffect[] {GIVE, HELP})
    };

    public static FatePatternType of(WuXingEffect master, PatternType patternType) {
        return fatePatternTypes[patternType.getValue() * 10 + master.getValue()];
    }
}
