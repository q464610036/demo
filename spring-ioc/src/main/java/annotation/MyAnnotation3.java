package annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 2、@Retention用来声明注解生效环境
 * Source表示源代码识别、class表示类识别、Runtime表示运行时识别，一般使用RUNTIME
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation3 {

}