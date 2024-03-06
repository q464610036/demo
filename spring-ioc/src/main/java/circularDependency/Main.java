package circularDependency;


import circularDependency.bean.Man;
import circularDependency.bean.Woman;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MainConfig.class);
        Woman woman = (Woman) applicationContext.getBean("woman");
        Man man = (Man) applicationContext.getBean("man");
        System.out.println();
    }
}
