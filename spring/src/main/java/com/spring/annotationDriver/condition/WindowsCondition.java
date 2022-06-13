package com.spring.annotationDriver.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 判断是否是windos
 *
 * @author 陈孟飞
 * @date 2021/6/15
 */
public class WindowsCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        //获取操作系统
        String name = environment.getProperty("os.name");
        if (name.contains("Windows")) {
            return true;
        }
        return false;
    }
}
