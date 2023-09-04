package com.example.serialise.stuff;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class JimRecord implements Serializable
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String name;

    public JimRecord(String theName)
    {
        super();

        this.name = theName;
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        final int fileVersion = (int) in.readLong();
        System.out.println("jim-file-version=" + fileVersion);

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

    private void writeObject(ObjectOutputStream out) throws IOException
    {
        out.writeLong(serialVersionUID);
        out.defaultWriteObject();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String newName)
    {
        this.name = newName;
    }

    public String toString()
    {
        return this.name;
    }
}
