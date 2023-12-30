package com.crafting.lox;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 */
final class Lox
{

    private static boolean hadError = false;

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

    private static void error(final int line, final String message)
    {
        Lox.report(line, "", message);
    }

    private static void report(final int line, final String where, final String message)
    {
        System.err.println("[line " + line + "] Error" + where + ": " + message);
        Lox.hadError = true;
    }

    private static void run(final String source)
    {
        try (final Scanner scanner = new Scanner(source))
        {
            for (final Token token : scanner.scanTokens())
            {
                System.out.println(token);
            }
        }
    }

    private static void runFile(final String path) throws IOException
    {
        final byte[] bytes = Files.readAllBytes(Paths.get(path));
        Lox.run(new String(bytes, Charset.defaultCharset()));

        if (Lox.hadError)
        {
            System.exit(65);
        }
    }

}
