package com.timzh.mp.play;

public abstract class BaseCreateRecordsDataTransformer<X extends TymRecord<X>, Y extends TymRecord<Y>>
extends
BaseDataTransformer<X, Y>
{
    private final Class<X> ourXclass;

    private final Class<Y> ourYclass;

    public BaseCreateRecordsDataTransformer(final Class<X> theXclass, final Class<Y> theYclass)
    {
        super();

        this.ourXclass = theXclass;
        this.ourYclass = theYclass;
    }

    @Override
    public X makeXfromY(final Y source) throws ReflectiveOperationException
    {
        return makeNewRecord(this.ourYclass, this.ourXclass, source);
    }

    @Override
    public Y makeYfromX(final X source) throws ReflectiveOperationException
    {
        return makeNewRecord(this.ourXclass, this.ourYclass, source);
    }

}
