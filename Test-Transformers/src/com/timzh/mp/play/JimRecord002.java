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
    }

    public JimRecord002(final BaseJimRecord002<?> that)
    {
        super(that);
    }

    @Override
    public JimRecord002 cloneRecord()
    {
        return new JimRecord002(this);
    }

}
