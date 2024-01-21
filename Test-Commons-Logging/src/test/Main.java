package test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

public class Main
{
    public Main()
    {
        super();
    }

    public static void main(final String[] args)
    {
        System.out.println("Hello, setting property...");

        System.setProperty("org.apache.commons.logging.diagnostics.dest", "STDOUT");

        System.out.println("Property has been set., configuring log4j...");

        BasicConfigurator.configure();
        PropertyConfigurator.configure("config/log4j.properties");

        System.out.println("Configured log4j, getting JCL log...");

        final Log log = LogFactory.getLog(Main.class);

        System.out.println("Got JCL log, printing stuff in-use...");

        System.out.println(LogFactory.getFactory());
        System.out.println(log);

        System.out.println("isDebugEnabled=" + log.isDebugEnabled());
        System.out.println("isWarnEnabled=" + log.isWarnEnabled());
        log.debug("Hello - debug");
        log.warn("Hello - warning");

        log.debug("Goodbye - debug");
        log.warn("Goodbye - warnig");
        System.out.println("Goodbye");
    }

}
