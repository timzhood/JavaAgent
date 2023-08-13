package com.timzh.mp.play;

public abstract class PhilRecord<T extends PhilRecord<T>>
{
    protected static final Integer NO_KEY = Integer.valueOf(-1);

    private String id = "";

    private Integer key = NO_KEY;

    public PhilRecord()
    {
        super();
    }

    public PhilRecord(final PhilRecord<?> that)
    {
        super();

        this.id = that.id;
        this.key = that.key;
    }

    public void clearKey()
    {
        this.key = NO_KEY;
    }

    public abstract T cloneRecord();

    public String getId()
    {
        return this.id;
    }

    public Integer getKey()
    {
        return this.key;
    }

    public void setId(final String newId)
    {
        this.id = newId;
    }

    public void setKey(final Integer newKey)
    {
        this.key = newKey;
    }

}
