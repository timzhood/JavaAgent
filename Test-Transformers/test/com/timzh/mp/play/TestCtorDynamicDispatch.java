package com.timzh.mp.play;

import org.junit.Test;

@SuppressWarnings("static-method")
public class TestCtorDynamicDispatch
{

    @SuppressWarnings("unused")
    @Test
    public void test()
    {
        final JimRecord001 jim001 = new JimRecord001();
        final JimRecord002 jim002 = new JimRecord002();

        // now create a JimRecord002 from these 2 different records, does it dispatch ?
        new JimRecord002(jim001);
        new JimRecord002(jim002);
    }

}
