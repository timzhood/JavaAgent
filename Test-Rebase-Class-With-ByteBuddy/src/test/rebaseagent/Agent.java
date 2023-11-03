package test.rebaseagent;

import java.lang.instrument.Instrumentation;
import java.util.concurrent.Callable;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.matcher.ElementMatchers;

public class Agent
{
    public static void premain(@SuppressWarnings("unused") final String args, final Instrumentation inst)
    {
        // FIXME: FreeTextEditorController and all subclasses including anonymous ones.
        // FIXME: Test intercept of subclasses and anonymous subclasses.

        new AgentBuilder.Default()
                .type(ElementMatchers.named("test.realcode.FreeTextEditorController"))
                .transform((b, td, cl, jm, pd) -> b
                        .method(ElementMatchers.named("canonicalToDisplayValue")
                                .or(ElementMatchers.named("displayToCanonicalValue")))
                        .intercept(MethodDelegation.to(Agent.class)))
                .installOn(inst);
    }

    @RuntimeType
    public static Object canonicalToDisplayValue(@SuperCall final Callable<?> c) throws Exception
    {
        final Object value = c.call();

        if (value instanceof final String stringValue)
        {
            return stringValue.replace("29.Y", "29.9a");
        }

        return value;
    }

    @RuntimeType
    public static Object displayToCanonicalValue(@SuperCall final Callable<?> c, @AllArguments final Object[] args) throws Exception
    {
        // FIXME: Need to alter input value to "displayToCanonicalValue".

        System.out.println("hello from displayToCanonicalValue " + args[0]);

        return c.call();
    }
}
