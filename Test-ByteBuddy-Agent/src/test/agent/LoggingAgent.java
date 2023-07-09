package test.agent;

import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType.Builder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;
import test.support.ToString;

public class LoggingAgent
{
    public static void premain(String arguments, Instrumentation instrumentation)
    {
        System.out.println("premain running ...");

        new AgentBuilder.Default().type(ElementMatchers.isAnnotatedWith(ToString.class))
                .transform(new AgentBuilder.Transformer()
                {
                    @Override
                    public Builder<?> transform(Builder<?> builder, TypeDescription typeDescription,
                            ClassLoader classLoader, JavaModule module, ProtectionDomain protectionDomain)
                    {
                        return builder.method(ElementMatchers.named("toString"))
                                .intercept(MethodDelegation.to(LoggerInterceptor.class));
                    }
                }).installOn(instrumentation);

        System.out.println("premain finished");
    }
}
