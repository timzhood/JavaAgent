package test.spoon;
/**
 * Reports warnings when empty catch blocks are found.
 */
public class CatchProcessor extends spoon.processing.AbstractProcessor<spoon.reflect.code.CtCatch> {
    @java.lang.Override
    public void process(final spoon.reflect.code.CtCatch element) {
        // we get all statements and if there isn't statement, it means the block catch is empty!
        if (element.getBody().getStatements().size() == 0) {
            this.getFactory().getEnvironment().report(this, spoon.support.Level.ERROR, element, "empty catch clause");
        }
    }

    public static void main(final java.lang.String[] args) {
        final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(test.spoon.CatchProcessor.class);
        logger.info("hello from logger");
        // final CtModel model = new FluentLauncher()
        // .complianceLevel(17)
        // .inputResource("src")
        // .noClasspath(true)
        // .outputDirectory("c:/temp/spoon-output")
        // .buildModel();
        // 
        // model.processWith(new CatchProcessor());
        try {
            final spoon.Launcher l = new spoon.Launcher();
            l.getEnvironment().setNoClasspath(true);
            // l.getEnvironment().setLevel("OFF");
            l.addInputResource("src");
            l.addProcessor(new test.spoon.CatchProcessor());
            l.run();
        } catch (final java.lang.Exception e) {
        }
    }
}