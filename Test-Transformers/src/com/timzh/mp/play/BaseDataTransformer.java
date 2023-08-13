package com.timzh.mp.play;

import java.lang.reflect.Constructor;
import java.util.Optional;

public abstract class BaseDataTransformer<X extends TymRecord<X>, Y extends TymRecord<Y>>
{
    public BaseDataTransformer()
    {
        super();
    }

    public abstract X makeXfromY(final Y source) throws ReflectiveOperationException;

    public abstract Y makeYfromX(final X source) throws ReflectiveOperationException;

    /**
     * @param <X>
     * @param <Y>
     * @param classX
     * @param classY
     * @return - return optional constructor
     */
    private static <X, Y> Optional<Constructor<X>> getConstructor(final Class<X> classX, final Class<Y> classY)
    {
        Optional<Constructor<X>> ctor = Optional.empty();

        Class<?> parameterClass = classY;

        while (parameterClass != null)
        {
            try
            {
                ctor = Optional.of(classX.getConstructor(parameterClass));
                break;
            }
            catch (@SuppressWarnings("unused") final NoSuchMethodException e)
            {
                parameterClass = parameterClass.getSuperclass();
            }
        }

        return ctor;
    }

    /**
     * @param <S>
     * @param <T>
     * @param classS
     * @param classT
     * @param source
     * @return - new record or null
     */
    protected static <S extends TymRecord<S>, T extends TymRecord<T>> T makeNewRecord(final Class<S> classS,
            final Class<T> classT,
            final S source)
    {
        T newRecord = null;

        final Optional<Constructor<T>> ctor = getConstructor(classT, classS);
        if (ctor.isPresent())
        {
            try
            {
                newRecord = ctor.get().newInstance(source);
                newRecord.clearKey();
            }
            catch (final ReflectiveOperationException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return newRecord;
    }

}
