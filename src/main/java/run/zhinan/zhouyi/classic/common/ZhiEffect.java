package run.zhinan.zhouyi.classic.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum ZhiEffect {
    // 地支六合
    Combine1 ("子丑合", EffectType.Combine , new Zhi[] {Zhi.ZI  , Zhi.CHOU}),
    Combine2 ("寅亥合", EffectType.Combine , new Zhi[] {Zhi.YIN , Zhi.HAI }),
    Combine3 ("卯戌合", EffectType.Combine , new Zhi[] {Zhi.MAO , Zhi.XU  }),
    Combine4 ("辰酉合", EffectType.Combine , new Zhi[] {Zhi.CHEN, Zhi.YOU }),
    Combine5 ("巳申合", EffectType.Combine , new Zhi[] {Zhi.SI  , Zhi.SHEN}),
    Combine6 ("午未合", EffectType.Combine , new Zhi[] {Zhi.WU  , Zhi.WEI }),

    // 地支六冲
    Conflict1("子午冲", EffectType.Conflict, new Zhi[] {Zhi.ZI  , Zhi.WU  }),
    Conflict2("丑未冲", EffectType.Conflict, new Zhi[] {Zhi.CHOU, Zhi.WEI }),
    Conflict3("寅申冲", EffectType.Conflict, new Zhi[] {Zhi.YIN , Zhi.SHEN}),
    Conflict4("卯酉冲", EffectType.Conflict, new Zhi[] {Zhi.MAO , Zhi.YOU }),
    Conflict5("辰戌冲", EffectType.Conflict, new Zhi[] {Zhi.CHEN, Zhi.XU  }),
    Conflict6("巳亥冲", EffectType.Conflict, new Zhi[] {Zhi.SI  , Zhi.HAI }),

    // 地支六害
    Harm1    ("子未穿", EffectType.Harm    , new Zhi[] {Zhi.ZI  , Zhi.WEI }),
    Harm2    ("午丑穿", EffectType.Harm    , new Zhi[] {Zhi.WU  , Zhi.CHOU}),
    Harm3    ("寅巳穿", EffectType.Harm    , new Zhi[] {Zhi.YIN , Zhi.SI  }),
    Harm4    ("卯辰穿", EffectType.Harm    , new Zhi[] {Zhi.MAO , Zhi.CHEN}),
    Harm5    ("申亥穿", EffectType.Harm    , new Zhi[] {Zhi.SHEN, Zhi.HAI }),
    Harm6    ("酉戌穿", EffectType.Harm    , new Zhi[] {Zhi.YOU , Zhi.XU  }),

    // 地支三合
    Combine31("寅午戌", EffectType.Combine , new Zhi[] {Zhi.YIN , Zhi.WU  , Zhi.XU  }),
    Combine32("巳酉丑", EffectType.Combine , new Zhi[] {Zhi.SI  , Zhi.YOU , Zhi.CHOU}),
    Combine33("申子辰", EffectType.Combine , new Zhi[] {Zhi.SHEN, Zhi.ZI  , Zhi.CHEN}),
    Combine34("亥卯未", EffectType.Combine , new Zhi[] {Zhi.HAI , Zhi.MAO , Zhi.WEI }),

    // 地支三会
    Gather31 ("寅卯辰", EffectType.Gather  , new Zhi[] {Zhi.YIN , Zhi.MAO , Zhi.CHEN}),
    Gather32 ("巳午未", EffectType.Gather  , new Zhi[] {Zhi.SI  , Zhi.WU  , Zhi.WEI }),
    Gather33 ("申酉戌", EffectType.Gather  , new Zhi[] {Zhi.SHEN, Zhi.YOU , Zhi.XU  }),
    Gather34 ("亥子丑", EffectType.Gather  , new Zhi[] {Zhi.HAI , Zhi.ZI  , Zhi.CHOU}),

    // 地支相刑
    Punish31 ("寅巳申", EffectType.Punish  , new Zhi[] {Zhi.YIN , Zhi.SI  , Zhi.SHEN}),
    Punish32 ("丑未戌", EffectType.Punish  , new Zhi[] {Zhi.CHOU, Zhi.WEI , Zhi.XU  }),
    Punish21 ("子卯刑", EffectType.Punish  , new Zhi[] {Zhi.ZI  , Zhi.MAO }),
    Punish55 ("辰辰刑", EffectType.Punish  , new Zhi[] {Zhi.CHEN, Zhi.CHEN}),
    Punish77 ("午午刑", EffectType.Punish  , new Zhi[] {Zhi.WU  , Zhi.WU  }),
    Punish10 ("酉酉刑", EffectType.Punish  , new Zhi[] {Zhi.YOU , Zhi.YOU }),
    Punish12 ("亥亥刑", EffectType.Punish  , new Zhi[] {Zhi.HAI , Zhi.HAI }),

    // 地支相破：子酉破、卯午破、辰丑破、戌未破、寅亥破、巳申破
    Break1   ("子酉破", EffectType.Break   , new Zhi[] {Zhi.ZI  , Zhi.YOU }),
    Break2   ("午卯破", EffectType.Break   , new Zhi[] {Zhi.WU  , Zhi.MAO }),
    Break3   ("辰丑破", EffectType.Break   , new Zhi[] {Zhi.CHEN, Zhi.CHOU}),
    Break4   ("未戌破", EffectType.Break   , new Zhi[] {Zhi.WEI , Zhi.XU  }),
    Break5   ("寅亥破", EffectType.Break   , new Zhi[] {Zhi.YIN , Zhi.HAI }),
    Break6   ("巳申破", EffectType.Break   , new Zhi[] {Zhi.SI  , Zhi.SHEN});

    public final static ZhiEffect[] combine6s = new ZhiEffect[] {Combine1 , Combine2 , Combine3 , Combine4 , Combine5 , Combine6 };
    public final static ZhiEffect[] conflicts = new ZhiEffect[] {Conflict1, Conflict2, Conflict3, Conflict4, Conflict5, Conflict6};

    String     name;
    EffectType type;
    Zhi[]      zhis;

    public boolean match(List<Zhi> zhis) {
        List<Zhi> zhiList = new ArrayList<>(zhis);
        boolean match = true;
        for (Zhi zhi : this.zhis) {
            match = match && zhiList.contains(zhi);
        }
        return match;
    }

    public static List<ZhiEffect> effect(Zhi self, Zhi other, ZhiEffect[] effects) {
        List<Zhi> zhiList = Arrays.asList(self, other);
        List<ZhiEffect> effectList = new ArrayList<>();
        for (ZhiEffect effect : effects) {
            if (effect.match(zhiList)) effectList.add(effect);
        }
        return effectList;
    }

    public static List<ZhiEffect> effect(Zhi self, Zhi other) {
        return effect(self, other, values());
    }

    public static boolean is6Combine (Zhi self, Zhi other) {
        return effect(self, other, combine6s).size() > 0;
    }

    public static boolean is6Conflict(Zhi self, Zhi other) {
        return effect(self, other, conflicts).size() > 0;
    }
}
