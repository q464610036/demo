package deferredImportSelector;

import deferredImportSelector.bean.Cat;
import deferredImportSelector.bean.Snake;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MainConfig.class);
        System.out.println("容器启动完毕");
        applicationContext.getBean(Cat.class);
    }
}
