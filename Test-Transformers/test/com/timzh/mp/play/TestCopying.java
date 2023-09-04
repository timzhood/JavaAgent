package com.timzh.mp.play;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.timzh.mp.play.utils.ObjectCopier;

public class TestCopying
{
    /**
     * Copy from JimRecord001 to JimRecord002.
     *
     * @throws ReflectiveOperationException -
     */
    @SuppressWarnings("static-method")
    @Test
    public void test001002() throws ReflectiveOperationException
    {
        System.out.println("started test001002");

        // arrange
        final JimRecord001 jim1 = new JimRecord001();
        jim1.setField001(Integer.valueOf(123));
        jim1.setId("jim1");
        jim1.setKey(Integer.valueOf(3));

        jim1.setStrangeKey(Integer.valueOf(4));

        final JimRecord002 jim2 = new JimRecord002();

        // action
        final ObjectCopier copier = new ObjectCopier("001", "002");
        copier.deepCopy(jim1, jim2);

        // assert
        assertTrue((Object) jim1 != (Object) jim2);
        assertEquals(123, jim2.getField001().intValue());
        assertEquals("jim1", jim2.getId());
        assertEquals(3, jim2.getKey().intValue());
        assertEquals(4, jim2.getStrangeKey().intValue());

        System.out.println("finished test001002");
    }

    /**
     * Copy from JimRecord002 to JimRecord001.
     *
     * @throws ReflectiveOperationException -
     */
    @SuppressWarnings("static-method")
    @Test
    public void test002001() throws ReflectiveOperationException
    {
        System.out.println("started test002001");

        // arrange
        final JimRecord002 jim1 = new JimRecord002();
        jim1.getBob().setNumber1(987);
        jim1.setField001(Integer.valueOf(123));
        jim1.setField002(Integer.valueOf(2));
        jim1.setId("jim1");
        jim1.setKey(Integer.valueOf(3));

        final List<String> names = new ArrayList<>();
        names.add("tim");
        names.add("hoody");
        jim1.setNames(names);

        final Map<String, Integer> ages = new HashMap<>();
        ages.put("tim hoody", Integer.valueOf(60));
        jim1.setAges(ages);

        jim1.setStrangeKey(Integer.valueOf(4));

        final JimRecord001 jim2 = new JimRecord001();

        // action
        final ObjectCopier copier = new ObjectCopier("002", "001");
        copier.deepCopy(jim1, jim2);

        // assert

        assertTrue((Object) jim1 != jim2);

        // check that the classes got swopped
        assertEquals(BobRecord002.class, jim1.getBob().getClass());
        assertEquals(BobRecord001.class, jim2.getBob().getClass());

        assertEquals(987, jim2.getBob().getNumber1());
        assertEquals(123, jim2.getField001().intValue());
        assertEquals("jim1", jim2.getId());
        assertEquals(3, jim2.getKey().intValue());
        assertEquals(4, jim2.getStrangeKey().intValue());

        System.out.println("finished test002001");
    }

    /**
     * Copy from JimRecord002 to JimRecord002.
     *
     * @throws ReflectiveOperationException -
     */
    @SuppressWarnings("static-method")
    @Test
    public void test002002() throws ReflectiveOperationException
    {
        System.out.println("started test002002");

        // arrange
        final JimRecord002 jim1 = new JimRecord002();
        jim1.setField001(Integer.valueOf(123));
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

        // action
        final ObjectCopier copier = new ObjectCopier("002", "002");
        copier.deepCopy(jim1, jim2);

        // assert
        assertTrue(jim1 != jim2);
        assertEquals(123, jim2.getField001().intValue());
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

        System.out.println("finished test002002");
    }

}
