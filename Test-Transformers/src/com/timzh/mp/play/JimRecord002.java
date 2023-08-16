package com.timzh.mp.play;

public final class JimRecord002 extends BaseJimRecord002<JimRecord002>
{
    public JimRecord002()
    {
        super();
    }

    public JimRecord002(final BaseJimRecord001<?> that)
    {
        super(that);
        System.out.println("creating a JimRecord002 from a BaseJimRecord001");
    }

    public JimRecord002(final BaseJimRecord002<?> that)
    {
        super(that);
        System.out.println("creating a JimRecord002 from a BaseJimRecord002");
    }

    @Override
    public JimRecord002 cloneRecord()
    {
        return new JimRecord002(this);
    }

}
