package run.zhinan.zhouyi.test;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import run.zhinan.zhouyi.classic.divine.liuyao.LiuYaoDescriber;
import run.zhinan.zhouyi.classic.divine.liuyao.LiuYaoDivine;
import run.zhinan.zhouyi.classic.divine.liuyao.LiuYaoDivineStatus;
import run.zhinan.zhouyi.classic.divine.liuyao.QuestionType;
import run.zhinan.zhouyi.classic.divine.meihua.MeiHuaDescriber;
import run.zhinan.zhouyi.classic.divine.meihua.MeiHuaDivine;

import java.time.LocalDateTime;

public class DivineTest {
    String question = "这次考试能过吗？";
    LocalDateTime divineTime = LocalDateTime.of(2023, 8, 28, 14, 15);
    int[] data = new int[] {9, 7, 9, 8, 6, 9};
    MeiHuaDivine meihua = MeiHuaDivine.init(question, divineTime, 56, 73, 2);
//    LiuYaoDivine liuyao = LiuYaoDivine.of(question, QuestionType.Love, divineTime, 1, data);

    @Test
    public void testMeiHuaDivine() {
        System.out.println(
                JSON.toJSONString(meihua, true)
        );
    }

    @Test
    public void testMeiHuaDescriber() {
        String description = "客户提了一个占卜请求，问题是：" + meihua.getQuestion() + "，占卜结果是：\n\n"
                + MeiHuaDescriber.describe(meihua)
                + "请根据占卜结果，给用户进行反馈，要求按照卦中给的意思进行描述，但是不要出现卦名，卦义，卦辞，爻辞这些专有名词，以及内容原文。"
                + "用通俗易懂的语言，尽可能清楚的描述推理答案。给客户清晰的建议，并告知客户占卜结果只能作为参考。";
        System.out.println(description);
    }

    @Test
    public void testLiuYaoDescriber() {
        int[] data = new int[] {8, 8, 9, 7, 6, 8};
        LiuYaoDivine liuyao = LiuYaoDivine.of("小胖有多想我", QuestionType.Love, LocalDateTime.now(), 0, data);
        System.out.println(LiuYaoDescriber.describe(LiuYaoDivineStatus.of(liuyao)));
    }

    @Test
    public void testLiuYaoDivine() {
        int[] data = new int[] {7, 6, 9, 8, 6, 9};
        LiuYaoDivine liuyao = LiuYaoDivine.of(question, QuestionType.Love, divineTime, 1, data);
//        System.out.println(JSON.toJSONString(liuyao, true));
        Assert.assertEquals(liuyao.getOrigin().getHexagram().getName(), "山火贲");
        Assert.assertEquals(liuyao.getResult().getHexagram().getName(), "水泽节");

        Assert.assertTrue(liuyao.getOrigin().isHarmony());
        Assert.assertTrue(liuyao.getResult().isHarmony());

        Assert.assertEquals(liuyao.getOrigin().getYao(0).getZhi().getName(), "卯");
        Assert.assertEquals(liuyao.getOrigin().getYao(1).getZhi().getName(), "丑");
        Assert.assertEquals(liuyao.getOrigin().getYao(2).getZhi().getName(), "亥");
        Assert.assertEquals(liuyao.getOrigin().getYao(3).getZhi().getName(), "戌");
        Assert.assertEquals(liuyao.getOrigin().getYao(4).getZhi().getName(), "子");
        Assert.assertEquals(liuyao.getOrigin().getYao(5).getZhi().getName(), "寅");

        Assert.assertEquals(liuyao.getResult().getYao(0).getZhi().getName(), "巳");
        Assert.assertEquals(liuyao.getResult().getYao(1).getZhi().getName(), "卯");
        Assert.assertEquals(liuyao.getResult().getYao(2).getZhi().getName(), "丑");
        Assert.assertEquals(liuyao.getResult().getYao(3).getZhi().getName(), "申");
        Assert.assertEquals(liuyao.getResult().getYao(4).getZhi().getName(), "戌");
        Assert.assertEquals(liuyao.getResult().getYao(5).getZhi().getName(), "子");
    }

    @Test
    public void testLiuYaoDivineStatus() {
        System.out.println(JSON.toJSONString(LiuYaoDivineStatus.of(
                LiuYaoDivine.of(question, QuestionType.Love, divineTime, 1, data)
        ), true));
    }
}
