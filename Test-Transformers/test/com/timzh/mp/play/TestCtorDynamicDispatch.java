package com.timzh.mp.play;

import org.junit.Test;

@SuppressWarnings("static-method")
public class TestCtorDynamicDispatch
{

    @SuppressWarnings("unused")
    @Test
    public void test()
    {
        final BaseJimRecord001<?> jim001 = new JimRecord001();
        final BaseJimRecord001<?> jim002 = new JimRecord002();

        // now create a JimRecord002 from these 2 different records, does it dispatch ?
        // new JimRecord002(jim001);
        // new JimRecord002(jim002);
        // no ...

        // but this does
        // new JimRecord002(new JimRecord001());
        // new JimRecord002(new JimRecord002());

        // what does this do?
        JimRecord002.create(jim001);
        JimRecord002.create(jim002);
    }

}
