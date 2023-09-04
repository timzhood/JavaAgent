package com.timzh.mp.play;

public abstract class BaseJimRecord001<T extends TymRecord<T>>
extends
TymRecord<T>
{
    private BaseBobRecord001 bob;

    private Integer field001 = Integer.valueOf(-1);

    public BaseJimRecord001()
    {
        super();

        this.bob = this.newBob();
    }

    public BaseJimRecord001(final BaseJimRecord001<?> that)
    {
        super(that);

        this.bob = that.bob.cloneRecord();
        this.field001 = that.field001;
    }

    public BaseBobRecord001 getBob()
    {
        return this.bob;
    }

    public Integer getField001()
    {
        return this.field001;
    }

    public void setBob(final BaseBobRecord001 newBob)
    {
        this.bob = newBob;
    }

    public void setField001(final Integer newField001)
    {
        this.field001 = newField001;
    }

    protected abstract BaseBobRecord001 newBob();

}
