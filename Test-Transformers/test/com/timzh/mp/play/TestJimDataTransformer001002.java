package com.timzh.mp.play;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings(
        { "boxing", "static-method" })
public class TestJimDataTransformer001002
{
    @Test
    public void testJimDataTransformer001002()
    {
        final JimDataTransformer001002 transformer = new JimDataTransformer001002();
        Assert.assertNotNull(transformer);
    }

    @Test
    public void testMakeXfromY()
    {
        final JimDataTransformer001002 transformer = new JimDataTransformer001002();
        Assert.assertNotNull(transformer);

        final Random randoms = new Random();

        final JimRecord002 jim002 = new JimRecord002();
        jim002.setField001(randoms.nextInt());

        final JimRecord001 jim001 = transformer.makeXfromY(jim002);
        Assert.assertNotNull(jim001);
        Assert.assertEquals(jim002.getField001(), jim001.getField001());
    }

    @Test
    public void testMakeYfromX()
    {
        final JimDataTransformer001002 transformer = new JimDataTransformer001002();
        Assert.assertNotNull(transformer);

        final Random randoms = new Random();

        final JimRecord001 jim001 = new JimRecord001();
        jim001.setField001(randoms.nextInt());

        final JimRecord002 jim002 = transformer.makeYfromX(jim001);
        Assert.assertNotNull(jim002);
        Assert.assertEquals(jim001.getField001(), jim002.getField001());
        Assert.assertEquals(Integer.valueOf(-1), jim002.getField002());
    }

}
