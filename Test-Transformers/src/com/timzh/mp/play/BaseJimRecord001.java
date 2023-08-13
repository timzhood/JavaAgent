package com.timzh.mp.play;

public abstract class BaseJimRecord001<T extends TymRecord<T>> extends TymRecord<T>
{
    private Integer field001 = Integer.valueOf(-1);

    public BaseJimRecord001()
    {
        super();
    }

    public BaseJimRecord001(final BaseJimRecord001<?> that)
    {
        super(that);

        this.field001 = that.field001;
    }

    public Integer getField001()
    {
        return this.field001;
    }

    public void setField001(final Integer newField001)
    {
        this.field001 = newField001;
    }

}
