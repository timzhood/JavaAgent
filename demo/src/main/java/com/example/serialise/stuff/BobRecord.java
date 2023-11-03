package com.example.serialise.stuff;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BobRecord implements Serializable
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int age;

    private List<JimRecord> jims = new ArrayList<>();

    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        final int fileVersion = (int) in.readLong();
        System.out.println("bob-file-version=" + fileVersion);

        switch (fileVersion)
        {
        case 1:
            break;

        default:
            System.err.println("don't understand serialised version " + fileVersion);
            break;
        }

        in.defaultReadObject();
    }

    private void writeObject(final ObjectOutputStream out) throws IOException
    {
        out.writeLong(serialVersionUID);
        out.defaultWriteObject();
    }

    public int getAge()
    {
        return this.age;
    }

    public List<JimRecord> getJims()
    {
        return this.jims;
    }

    public void setAge(final int newAge)
    {
        this.age = newAge;
    }

    public void setJims(final List<JimRecord> newJims)
    {
        this.jims.clear();
        this.jims.addAll(newJims);
    }

    @Override
    public String toString()
    {
        return this.age + "," + Arrays.toString(this.jims.toArray());
    }

}
