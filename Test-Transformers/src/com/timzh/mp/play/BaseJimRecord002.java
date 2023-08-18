package com.timzh.mp.play;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseJimRecord002<T extends BaseJimRecord001<T>> extends BaseJimRecord001<T>
{
    // NB: initialise as declared rather than in all the ctors below
    private Integer field002 = Integer.valueOf(-1);

    private List<String> names = new ArrayList<>();

    public BaseJimRecord002()
    {
        super();
    }

    public BaseJimRecord002(final BaseJimRecord001<?> that)
    {
        super(that);
    }

    public BaseJimRecord002(final BaseJimRecord002<?> that)
    {
        super(that);

        this.field002 = that.field002;
    }

    public Integer getField002()
    {
        return this.field002;
    }

    public void setField002(final Integer newField002)
    {
        this.field002 = newField002;
    }

    public List<String> getNames()
    {
        return this.names;
    }

    public void setNames(final List<String> newNames)
    {
        this.names = newNames;
    }

}
