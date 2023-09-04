package com.timzh.mp.play.utils;

import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

public class ObjectCopier
{
    private final String destSuffix;

    private final String origSuffix;

    public ObjectCopier(final String theOrigSuffix, final String theDestSuffix)
    {
        super();

        this.destSuffix = theDestSuffix;
        this.origSuffix = theOrigSuffix;
    }

    private static boolean isImmutable(final Object oldValue)
    {
        //@formatter:off
        return oldValue instanceof Boolean
                || oldValue instanceof Character
                || oldValue instanceof Enum<?>
        || oldValue instanceof Number
        || oldValue instanceof String;
        //@formatter:on
    }

    /**
     * Deep copy Java Beans, see definition https://en.wikipedia.org/wiki/JavaBeans.
     *
     * @param orig - copy from this object
     * @param dest - to this object
     * @throws ReflectiveOperationException - thrown if something goes wrong
     */
    public void deepCopy(final Object orig, final Object dest)
            throws ReflectiveOperationException
    {
        for (final PropertyDescriptor origDescriptor : PropertyUtils.getPropertyDescriptors(orig))
        {
            final String name = origDescriptor.getName();
            System.out.println(name);

            if (PropertyUtils.isReadable(orig, name) && PropertyUtils.isWriteable(dest, name))
            {
                final Class<?> type = origDescriptor.getPropertyType();
                final Object value = PropertyUtils.getSimpleProperty(orig, name);

                if (ClassUtils.isPrimitiveOrWrapper(type) || isImmutable(value))
                {
                    System.out.println("setting primitive or immutable...");
                    PropertyUtils.setSimpleProperty(dest, name, value);
                }
                else if (value instanceof List)
                {
                    System.out.println("setting List...");
                    PropertyUtils.setSimpleProperty(dest, name, this.deepCopyList(value));
                }
                else if (value instanceof Map)
                {
                    System.out.println("setting Map...");
                    PropertyUtils.setSimpleProperty(dest, name, this.deepCopyMap(value));
                }
                else if (StringUtils.startsWith(value.getClass().getPackage().getName(), "com.timzh.mp."))
                {
                    System.out.println("setting Object...");
                    PropertyUtils.setSimpleProperty(dest, name, this.createAndCopy(value));
                }
                else
                {
                    System.err.println("Error can't handle '" + name + "' on class '" + dest.getClass() + "'");
                }
            }
            else
            {
                // not
                System.err.println("Error can't read or write '" + name + "' on class '" + dest.getClass() + "'");
            }
        }
    }

    public Class<?> getNewClass(final Class<?> oldClazz) throws ClassNotFoundException
    {
        final Class<?> newClazz;

        final String oldClazzName = oldClazz.getName();
        if (StringUtils.endsWith(oldClazzName, this.origSuffix))
        {
            final String newClazzName = StringUtils.removeEnd(oldClazzName, this.origSuffix) + this.destSuffix;
            newClazz = ClassUtils.getClass(newClazzName);
        }
        else
        {
            newClazz = oldClazz;
        }

        System.out.println(String.format("getNewClass,old=[%s],new=[%s]", oldClazz, newClazz));

        return newClazz;
    }

    private Object createAndCopy(final Object oldValue) throws ReflectiveOperationException
    {
        System.out.println(oldValue);

        final Class<?> newClazz = this.getNewClass(oldValue.getClass());
        final Object newValue = newClazz.getConstructor().newInstance();
        this.deepCopy(oldValue, newValue);
        return newValue;
    }

    private List<Object> deepCopyList(final Object value) throws ReflectiveOperationException
    {
        @SuppressWarnings("unchecked")
        final List<Object> oldList = (List<Object>) value;

        @SuppressWarnings("boxing")
        final List<Object> newList = oldList.getClass().getConstructor(Integer.TYPE).newInstance(oldList.size());
        for (final Object oldValue : oldList)
        {
            if (isImmutable(oldValue))
            {
                newList.add(oldValue);
            }
            else
            {
                newList.add(this.createAndCopy(oldValue));
            }
        }

        return newList;
    }

    private Map<Object, Object> deepCopyMap(final Object value) throws ReflectiveOperationException
    {
        @SuppressWarnings("unchecked")
        final Map<Object, Object> oldMap = (Map<Object, Object>) value;

        @SuppressWarnings("boxing")
        final Map<Object, Object> newMap = oldMap.getClass().getConstructor(Integer.TYPE).newInstance(oldMap.size());

        // FIXME: Can I assume keys are immutable ?
        for (final Entry<Object, Object> oldItem : oldMap.entrySet())
        {
            if (isImmutable(oldItem.getValue()))
            {
                newMap.put(oldItem.getKey(), oldItem.getValue());
            }
            else
            {
                newMap.put(oldItem.getKey(), this.createAndCopy(oldItem.getValue()));
            }
        }

        return newMap;
    }

}
