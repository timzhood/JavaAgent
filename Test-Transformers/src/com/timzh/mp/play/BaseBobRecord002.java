package com.timzh.mp.play;

public abstract class BaseBobRecord002
extends
BaseBobRecord001
{
    private int number2 = 1;

    public BaseBobRecord002()
    {
        super();
    }

    public int getNumber2()
    {
        return this.number2;
    }

    public void setNumber2(final int newNumber2)
    {
        this.number2 = newNumber2;
    }

    @Override
    public abstract BaseBobRecord002 cloneRecord();

}
