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



