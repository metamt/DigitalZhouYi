package run.zhinan.zhouyi.classic.divine.liuyao;

import lombok.Getter;
import run.zhinan.zhouyi.classic.common.Gan;
import run.zhinan.zhouyi.classic.common.Zhi;
import run.zhinan.zhouyi.classic.divine.common.BasicHexagram;
import run.zhinan.zhouyi.classic.divine.common.CompositeHexagram;
import run.zhinan.zhouyi.common.WuXing;
import run.zhinan.zhouyi.common.WuXingEffect;
import run.zhinan.zhouyi.common.YinYang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static run.zhinan.zhouyi.classic.divine.common.CompositeHexagram.*;

@Getter
public class LiuYaoHexagram {
    CompositeHexagram hexagram;
    YinYang yinYang;
    WuXing  wuXing;

    List<HexagramYao> yaos = new ArrayList<>();

    Position self;  // 世爻
    Position ying;  // 应爻

    public static LiuYaoHexagram of(int[] data) {
        LiuYaoHexagram liuyao = new LiuYaoHexagram();
        // 卦象信息
        liuyao.hexagram = CompositeHexagram.getByCode(buildCode(data));
        liuyao.yinYang  = liuyao.hexagram.getPalace().getYinYang();
        liuyao.wuXing   = liuyao.hexagram.getPalace().getWuXing ();
        int generation  = liuyao.hexagram.getGeneration().getValue();
        liuyao.self     = Position.getByValue(selfPositions[generation]);
        liuyao.ying     = Position.getByValue(yingPositions[generation]);

        List<WuXing> wuXingList = new ArrayList<>(Arrays.asList(WuXing.values()));
        // 纳甲装卦
        for (int i = 0; i < 6; i++) {
            BasicHexagram gua = i < 3 ? liuyao.hexagram.getUnder() : liuyao.hexagram.getUpper();
            Gan gan = gans[i / 3][gua.getInitValue() - 1];
            Zhi zhi = zhis[i / 3][gua.getInitValue() - 1][i % 3];
            YinYang  yinYang   = YinYang.getByValue(data[i] % 2);
            WuXing   wuXing    = zhi.getWuXing();
            Relation relation  = Relation.getByValue(WuXingEffect.of(liuyao.getWuXing(), zhi.getWuXing()).getValue());
            Position position = Position.getByValue(i);
            HexagramYao yao = new HexagramYao(gan, zhi, yinYang, wuXing, relation, position);
            if (position.equals(liuyao.self)) yao.self = true;
            if (position.equals(liuyao.ying)) yao.ying = true;
            if (data[i] % 3 != 0) yao.change = true;
            liuyao.yaos.add(yao);

            // 从五行列表中移除已上卦的五行，剩下为没有上卦的伏爻
            wuXingList.remove(wuXing);
        }

        // 计算伏爻
        BasicHexagram gua = liuyao.getHexagram().getPalace();
        for (WuXing wuXing : wuXingList) {
            for (int i = 0; i < 6; i++) {
                Zhi zhi = zhis[i / 3][gua.getInitValue() - 1][i % 3];
                if (zhi.getWuXing().equals(wuXing)) {
                    Gan gan = gans[i / 3][gua.getInitValue() - 1];
                    YinYang  yinYang   = YinYang.getByValue(data[i] % 2);
                    Relation relation  = Relation.getByValue(WuXingEffect.of(liuyao.getWuXing(), zhi.getWuXing()).getValue());
                    Position position = Position.HIDDEN;
                    HexagramYao fly = liuyao.yaos.get(i);
                    HexagramYao yao = new HexagramYao(gan, zhi, yinYang, wuXing, relation, position);
                    fly.fly = true;
                    fly.hidden = yao;
                }
            }
        }
        return liuyao;
    }

    static String buildCode(int[] data) {
        StringBuilder sb = new StringBuilder();
        for (int d : data) {
            sb.append(d % 2);
        }
        return sb.toString();
    }

    public boolean isHarmony () {
        return harmonyHexagrams .contains(hexagram);
    }

    public boolean isConflict() {
        return conflictHexagrams.contains(hexagram);
    }

    public HexagramYao getYao(int i) {
        return yaos.get(i);
    }

    public HexagramYao getSelfYao() {
        return getYao(self.getValue());
    }

    public HexagramYao getYingYao() {
        return getYao(ying.getValue());
    }

    public final static List<CompositeHexagram> harmonyHexagrams  = Arrays.asList(
            H18, H81, H84, H48, H37, H73, H26, H62
    );
    public final static List<CompositeHexagram> conflictHexagrams = Arrays.asList(
            H11, H22, H33, H44, H55, H66, H77, H88, H14, H41
    );
    public final static Zhi[][][] zhis =
            {{
                    {Zhi.ZI  , Zhi.YIN , Zhi.CHEN},
                    {Zhi.SI  , Zhi.MAO , Zhi.CHOU},
                    {Zhi.MAO , Zhi.CHOU, Zhi.HAI },
                    {Zhi.ZI  , Zhi.YIN , Zhi.CHEN},
                    {Zhi.CHOU, Zhi.HAI , Zhi.YOU },
                    {Zhi.YIN , Zhi.CHEN, Zhi.WU  },
                    {Zhi.CHEN, Zhi.WU  , Zhi.SHEN},
                    {Zhi.WEI , Zhi.SI  , Zhi.MAO }
            },{
                    {Zhi.WU  , Zhi.SHEN, Zhi.XU  },
                    {Zhi.HAI , Zhi.YOU , Zhi.WEI },
                    {Zhi.YOU , Zhi.WEI , Zhi.SI  },
                    {Zhi.WU  , Zhi.SHEN, Zhi.XU  },
                    {Zhi.WEI , Zhi.SI  , Zhi.MAO },
                    {Zhi.SHEN, Zhi.XU  , Zhi.ZI  },
                    {Zhi.XU  , Zhi.ZI  , Zhi.YIN },
                    {Zhi.CHOU, Zhi.HAI , Zhi.YOU }
            }};
    public final static Gan[][] gans = {
            {Gan.JIA , Gan.DING, Gan.JI  , Gan.GENG, Gan.XIN , Gan.WU  , Gan.BING, Gan.YI },
            {Gan.REN , Gan.DING, Gan.JI  , Gan.GENG, Gan.XIN , Gan.WU  , Gan.BING, Gan.GUI}
    };
    public final static int[] selfPositions = {6, 1, 2, 3, 4, 5, 4, 3};
    public final static int[] yingPositions = {3, 4, 5, 6, 1, 2, 1, 6};
}
