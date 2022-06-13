package com;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;
import java.lang.instrument.Instrumentation;
public class AgentMain {
    public static void premain(String agentArgs, Instrumentation inst) {
        //创建一个转换器，转换器可以修改类的实现
        //ByteBuddy对java agent提供了转换器的实现，直接使用即可
        //1.拦截哪些方法
        AgentBuilder.Transformer transformer = new AgentBuilder.Transformer() {
            @Override
            public DynamicType.Builder<?> transform(
                    DynamicType.Builder<?> builder, TypeDescription typeDescription,
                    ClassLoader classLoader, JavaModule javaModule) {
                // ElementMatchers.<MethodDescription>any()拦截任意方法
                return builder.method(ElementMatchers.<MethodDescription>any())
                    // 拦截到的方法委托给TimeInterceptor
                    .intercept(MethodDelegation.to(MyInterceptor.class));
            }
        };

        //2.拦截哪些包
        new AgentBuilder // Byte Buddy专门有个AgentBuilder来处理Java Agent的场景
            .Default()
            // 根据包名前缀拦截类
            .type(ElementMatchers.nameStartsWith("com.agent"))
            // 拦截到的类由transformer处理
            .transform(transformer).installOn(inst);
    }
}