package com.timzh.mp.play;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ClassUtils;
import org.junit.Test;

public class TestCopying
{

    @SuppressWarnings("static-method")
    @Test
    public void test() throws ReflectiveOperationException
    {
        final JimRecord002 jim1 = new JimRecord002();
        jim1.setField001(Integer.valueOf(1));
        jim1.setField002(Integer.valueOf(2));
        jim1.setId("jim1");
        jim1.setKey(Integer.valueOf(3));

        final List<String> names = new ArrayList<>();
        names.add("tim");
        names.add("hood");
        jim1.setNames(names);

        final Map<String, Integer> ages = new HashMap<>();
        ages.put("tim hood", Integer.valueOf(60));
        jim1.setAges(ages);

        jim1.setStrangeKey(Integer.valueOf(4));

        final JimRecord002 jim2 = new JimRecord002();
        deepCopy(jim1, jim2);

        assertTrue(jim1 != jim2);
        assertEquals(1, jim2.getField001().intValue());
        assertEquals(2, jim2.getField002().intValue());
        assertEquals("jim1", jim2.getId());
        assertEquals(3, jim2.getKey().intValue());
        assertEquals(4, jim2.getStrangeKey().intValue());

        assertTrue(names != jim2.getNames());
        assertTrue(Arrays.asList("tim", "hood").equals(jim2.getNames()));

        assertTrue(ages != jim2.getAges());
        final Map<String, Integer> ages2 = new HashMap<>();
        ages2.put("tim hood", Integer.valueOf(60));
        assertTrue(ages2.equals(jim2.getAges()));
    }

    /**
     * Deep copy Java Beans, see definition https://en.wikipedia.org/wiki/JavaBeans.
     *
     * @param orig - copy from this object
     * @param dest - to this object
     * @throws ReflectiveOperationException - thrown if something goes wrong
     */
    private static void deepCopy(final Object orig, final Object dest)
            throws ReflectiveOperationException
    {
        for (final PropertyDescriptor origDescriptor : PropertyUtils.getPropertyDescriptors(orig))
        {
            final String name = origDescriptor.getName();

            if (PropertyUtils.isReadable(orig, name) && PropertyUtils.isWriteable(dest, name))
            {
                final Class<?> type = origDescriptor.getPropertyType();
                final Object value = PropertyUtils.getSimpleProperty(orig, name);

                if (ClassUtils.isPrimitiveOrWrapper(type) || isImmutable(value))
                {
                    PropertyUtils.setSimpleProperty(dest, name, value);
                }
                else if (value instanceof List)
                {
                    PropertyUtils.setSimpleProperty(dest, name, deepCopyList(value));
                }
                else if (value instanceof Map)
                {
                    PropertyUtils.setSimpleProperty(dest, name, deepCopyMap(value));
                }
                else
                {
                    System.err.println("Error can't handle '" + name + "' on class '" + dest.getClass() + "'");
                }
            }
            else
            {
                System.err.println("Error can't read or write '" + name + "' on class '" + dest.getClass() + "'");
            }
        }
    }

    private static List<Object> deepCopyList(final Object value) throws ReflectiveOperationException
    {
        @SuppressWarnings("unchecked")
        final List<Object> oldList = (List<Object>) value;

        // FIXME: Really this should create the same kind of list...
        final List<Object> newList = new ArrayList<>(oldList.size());
        for (final Object oldValue : oldList)
        {
            if (isImmutable(oldValue))
            {
                newList.add(oldValue);
            }
            else
            {
                newList.add(createAndCopy(oldValue));
            }
        }

        return newList;
    }

    private static Map<Object, Object> deepCopyMap(final Object value) throws ReflectiveOperationException
    {
        @SuppressWarnings("unchecked")
        final Map<Object, Object> oldMap = (Map<Object, Object>) value;

        // FIXME: Check this creates the same kind of map and can we pre-size the map ?
        final Map<Object, Object> newMap = oldMap.getClass().getDeclaredConstructor().newInstance();

        // FIXME: Can I assume keys are immutable ?
        for (final Entry<Object, Object> oldItem : oldMap.entrySet())
        {
            if (isImmutable(oldItem.getValue()))
            {
                newMap.put(oldItem.getKey(), oldItem.getValue());
            }
            else
            {
                newMap.put(oldItem.getKey(), createAndCopy(oldItem.getValue()));
            }
        }

        return newMap;
    }

    private static Object createAndCopy(final Object oldValue) throws ReflectiveOperationException
    {
        final Object newValue = oldValue.getClass().getDeclaredConstructor().newInstance();
        deepCopy(oldValue, newValue);
        return newValue;
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
}
