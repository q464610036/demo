package annotationDriver.annotation;

import java.lang.annotation.*;



//加上Type为类的使用范围，class可使用的注解
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Photo {
}


