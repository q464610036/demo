package annotation;

import java.lang.annotation.*;

/**
 * @author 陈孟飞
 * 2021/6/10
 */
@MyAnnotation2(name = "婴儿", age = 1)
public class MetaAnnotation {

    @MyAnnotation1
    public void test() {

    }
}

/**
 * 1、@Target用来声明注解使用范围，类、变量、方法、包
 */
//这里只制定了Method范围，所以该注解无法给class
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@interface MyAnnotation1 {
    String name() default "小伙子";

    int age() default 18;
}

//加上Type为类的使用范围，class可使用的注解
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@interface MyAnnotation2 {
    //看起来是方法，实际上是注解参数的名称，注解定义的参数，少任何一个都会报错,除非有default。默认名称是value
    String name();

    int age();

    String[] likes() default {};
}

/**
 * 2、@Retention用来声明注解生效环境
 * Source表示源代码识别、class表示类识别、Runtime表示运行时识别，一般使用RUNTIME
 */
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation3 {

}

/**
 * 3、@Documented是否显示java注解
 */
@Documented
@interface MyAnnotation4 {

}

/**
 * 4、@Inherited表示当前注解是否能够被继承
 */
@Inherited
@interface MyAnnotation5 {

}


