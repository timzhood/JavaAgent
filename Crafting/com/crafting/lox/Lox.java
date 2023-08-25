package com.crafting.lox;

import org.jetbrains.annotations.NonNls;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 *
 */
@NonNls
final class Lox
{

    private Lox()
    {
        super();
    }

    public static void main(final String[] args) throws IOException
    {
        if (args.length != 1)
        {
            System.out.println("Usage: jlox [script]");
            System.exit(64);
        }

        Lox.runFile(args[0]);
    }

    private static void runFile(final String path) throws IOException
    {
        final byte[] bytes = Files.readAllBytes(Paths.get(path));
        Lox.run(new String(bytes, Charset.defaultCharset()));
    }

    private static void run(final String source)
    {
        try (final Scanner scanner = new Scanner(source))
        {
//            for (Token token : scanner.scanTokens())
//            {
//                System.out.println(token);
//            }
        }
    }

}
