package test.spoon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spoon.Launcher;
import spoon.OutputType;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtEnum;
import spoon.reflect.declaration.CtEnumValue;
import spoon.support.Level;

public class EnumProcessor
        extends
        AbstractProcessor<CtEnum<Enum<?>>>
{

    public EnumProcessor()
    {
        super();
    }

    @Override
    public void process(final CtEnum<Enum<?>> element)
    {
        for (final CtEnumValue<?> en : element.getEnumValues())
        {
            this.getFactory().getEnvironment().report(this, Level.ERROR, en, en.toString());
        }
    }

    public static void main(final String[] args)
    {
        final Logger logger = LoggerFactory.getLogger(CatchProcessor.class);

        logger.info("hello from logger");

        try
        {
            final Launcher l = new Launcher();
            l.getEnvironment().setLevel(Level.ERROR.name());
            l.getEnvironment().setNoClasspath(true);
            l.getEnvironment().setOutputType(OutputType.NO_OUTPUT);
            // l.addInputResource("src");
            l.addInputResource("C:\\My_Workspaces\\Eclipse-2023-06");
            l.addProcessor(new EnumProcessor());
            l.run();
        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }
    }

}
