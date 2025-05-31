package annotation;

import java.lang.annotation.*;

/**
 * 1、@Target用来声明注解使用范围，类、变量、方法、包
 */
//这里只制定了Method范围，所以该注解无法给class
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MyAnnotation1 {
    String name() default "小伙子";

    int age() default 18;
}
