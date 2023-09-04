package com.example.serialise.stuff;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Hello world!
 */
public final class App
{
    private App()
    {
    }

    /**
     * Says hello to the world.
     * 
     * @param args
     *            The arguments of the program.
     */
    public static void main(String[] args)
    {
        System.out.println("Hello World!");

        final BobRecord bob = new BobRecord();
        bob.setAge(123);
        bob.setJims(Arrays.asList(new JimRecord("Tim"), new JimRecord("Will")));

        // try (ObjectOutputStream oos = new
        // ObjectOutputStream(Files.newOutputStream(Paths.get("c:/temp/oos.bin"))))
        // {
        // oos.writeObject(bob);
        // System.out.println("wrote-out-this=" + bob);
        // }
        // catch (Exception e)
        // {
        // e.printStackTrace();
        // }

        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get("c:/temp/oos.bin"))))
        {
            Object bob2 = ois.readObject();
            assert bob2 instanceof BobRecord;
            System.out.println("read-in-this=" + bob2);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
