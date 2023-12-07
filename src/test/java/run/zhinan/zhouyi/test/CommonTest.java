package run.zhinan.zhouyi.test;

import org.junit.jupiter.api.Test;
import org.testng.Assert;
import run.zhinan.zhouyi.classic.common.GanZhi;
import run.zhinan.zhouyi.common.WuXing;
import run.zhinan.zhouyi.common.WuXingEffect;

public class CommonTest {
    @Test
    public void testWuXing() {
        Assert.assertEquals(WuXing.WOOD , WuXing.getByName("木"));
        Assert.assertEquals(WuXing.FIRE , WuXing.getByName("火"));
        Assert.assertEquals(WuXing.EARTH, WuXing.getByName("土"));
        Assert.assertEquals(WuXing.METAL, WuXing.getByName("金"));
        Assert.assertEquals(WuXing.WATER, WuXing.getByName("水"));
        Assert.assertNull(WuXing.getByName("雷"));
        Assert.assertNull(WuXing.getByName(null));
    }

    @Test
    public void testWuXingEffect() {
        Assert.assertEquals(WuXing.WATER, WuXingEffect.GIVE.of(WuXing.WOOD));
        Assert.assertEquals(WuXing.WOOD , WuXingEffect.HELP.of(WuXing.WOOD));
        Assert.assertEquals(WuXing.FIRE , WuXingEffect.LEAK.of(WuXing.WOOD));
        Assert.assertEquals(WuXing.EARTH, WuXingEffect.COST.of(WuXing.WOOD));
        Assert.assertEquals(WuXing.METAL, WuXingEffect.CURB.of(WuXing.WOOD));

        Assert.assertEquals(WuXing.WOOD , WuXingEffect.GIVE.of(WuXing.FIRE));
        Assert.assertEquals(WuXing.FIRE , WuXingEffect.HELP.of(WuXing.FIRE));
        Assert.assertEquals(WuXing.EARTH, WuXingEffect.LEAK.of(WuXing.FIRE));
        Assert.assertEquals(WuXing.METAL, WuXingEffect.COST.of(WuXing.FIRE));
        Assert.assertEquals(WuXing.WATER, WuXingEffect.CURB.of(WuXing.FIRE));

        Assert.assertEquals(WuXing.FIRE , WuXingEffect.GIVE.of(WuXing.EARTH));
        Assert.assertEquals(WuXing.EARTH, WuXingEffect.HELP.of(WuXing.EARTH));
        Assert.assertEquals(WuXing.METAL, WuXingEffect.LEAK.of(WuXing.EARTH));
        Assert.assertEquals(WuXing.WATER, WuXingEffect.COST.of(WuXing.EARTH));
        Assert.assertEquals(WuXing.WOOD , WuXingEffect.CURB.of(WuXing.EARTH));

        Assert.assertEquals(WuXing.EARTH, WuXingEffect.GIVE.of(WuXing.METAL));
        Assert.assertEquals(WuXing.METAL, WuXingEffect.HELP.of(WuXing.METAL));
        Assert.assertEquals(WuXing.WATER, WuXingEffect.LEAK.of(WuXing.METAL));
        Assert.assertEquals(WuXing.WOOD , WuXingEffect.COST.of(WuXing.METAL));
        Assert.assertEquals(WuXing.FIRE , WuXingEffect.CURB.of(WuXing.METAL));

        Assert.assertEquals(WuXing.METAL, WuXingEffect.GIVE.of(WuXing.WATER));
        Assert.assertEquals(WuXing.WATER, WuXingEffect.HELP.of(WuXing.WATER));
        Assert.assertEquals(WuXing.WOOD , WuXingEffect.LEAK.of(WuXing.WATER));
        Assert.assertEquals(WuXing.FIRE , WuXingEffect.COST.of(WuXing.WATER));
        Assert.assertEquals(WuXing.EARTH, WuXingEffect.CURB.of(WuXing.WATER));
    }

    @Test
    public void testGanZhi() {
        GanZhi JIA_ZI = GanZhi.getByValue(1);
        Assert.assertEquals(GanZhi.getByName("甲子"), JIA_ZI);
        Assert.assertEquals(GanZhi.getByName("乙丑"), JIA_ZI.roll( 1));
        Assert.assertEquals(GanZhi.getByName("癸亥"), JIA_ZI.roll(-1));
    }
}
