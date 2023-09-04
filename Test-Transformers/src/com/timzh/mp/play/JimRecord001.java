package com.timzh.mp.play;

public final class JimRecord001 extends BaseJimRecord001<JimRecord001>
{
    public JimRecord001()
    {
        super();
    }

    public JimRecord001(final BaseJimRecord001<JimRecord001> that)
    {
        super(that);
    }

    @Override
    public JimRecord001 cloneRecord()
    {
        return new JimRecord001(this);
    }

    @Override
    protected BaseBobRecord001 newBob()
    {
        return new BobRecord001();
    }

}
