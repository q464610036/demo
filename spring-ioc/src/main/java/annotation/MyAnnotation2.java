package annotation;

import java.lang.annotation.*;

//加上Type为类的使用范围，class可使用的注解
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MyAnnotation2 {
    //看起来是方法，实际上是注解参数的名称，注解定义的参数，少任何一个都会报错,除非有default。默认名称是value
    String name();

    int age();

    String[] likes() default {};
}