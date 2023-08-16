package com.timzh.mp.play;

public final class JimRecord002
extends
BaseJimRecord002<JimRecord002>
{
    public JimRecord002()
    {
        super();
    }

    private JimRecord002(final BaseJimRecord001<?> that)
    {
        super(that);
        System.out.println("creating a JimRecord002 from a BaseJimRecord001");
    }

    private JimRecord002(final BaseJimRecord002<?> that)
    {
        super(that);
        System.out.println("creating a JimRecord002 from a BaseJimRecord002");
    }

    @Override
    public JimRecord002 cloneRecord()
    {
        return new JimRecord002(this);
    }

    public static JimRecord002 create(final BaseJimRecord001<?> that)
    {
        final JimRecord002 result;

        if (that instanceof BaseJimRecord002)
        {
            result = new JimRecord002((BaseJimRecord002<?>) that);
        }
        else if (that != null)
        {
            result = new JimRecord002(that);
        }
        else
        {
            result = null;
            System.err.println("oops");
        }

        return result;
    }

}
