package test.spoon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spoon.Launcher;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtCatch;
import spoon.support.Level;

/**
 * Reports warnings when empty catch blocks are found.
 */
public class CatchProcessor
        extends
        AbstractProcessor<CtCatch>
{
    @Override
    public void process(final CtCatch element)
    {
        // we get all statements and if there isn't statement, it means the block catch is empty!
        if (element.getBody().getStatements().size() == 0)
        {
            this.getFactory().getEnvironment().report(this, Level.ERROR, element, "empty catch clause");
        }
    }

    public static void main(final String[] args)
    {
        final Logger logger = LoggerFactory.getLogger(CatchProcessor.class);

        logger.info("hello from logger");

//        final CtModel model = new FluentLauncher()
//                .complianceLevel(17)
//                .inputResource("src")
//                .noClasspath(true)
//                .outputDirectory("c:/temp/spoon-output")
//                .buildModel();
//
//        model.processWith(new CatchProcessor());

        try
        {
            final Launcher l = new Launcher();
            l.getEnvironment().setNoClasspath(true);
            // l.getEnvironment().setLevel("OFF");
            l.addInputResource("src");
            l.addProcessor(new CatchProcessor());
            l.run();
        }
        catch (final Exception e)
        {

        }
    }
}
