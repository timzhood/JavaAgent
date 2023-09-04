package com.timzh.mp.play;

public abstract class BaseBobRecord001
extends
TymRecord<BaseBobRecord001>
{
    private int number1 = 1;

    public BaseBobRecord001()
    {
        super();
    }

    public int getNumber1()
    {
        return this.number1;
    }

    public void setNumber1(final int newNumber1)
    {
        this.number1 = newNumber1;
    }

    @Override
    public abstract BaseBobRecord001 cloneRecord();

}
