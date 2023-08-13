package com.timzh.mp.play;

public abstract class TymRecord<T extends PhilRecord<T>> extends PhilRecord<T>
{
    // NB: initialise as declared rather than in all the ctors below
    private Integer strangeKey = NO_KEY;

    public TymRecord()
    {
        super();
    }

    public TymRecord(final TymRecord<?> that)
    {
        super();

        this.strangeKey = that.strangeKey;
    }

    public Integer getStrangeKey()
    {
        return this.strangeKey;
    }

    public void setStrangeKey(final Integer newStrangeKey)
    {
        this.strangeKey = newStrangeKey;
    }

}
