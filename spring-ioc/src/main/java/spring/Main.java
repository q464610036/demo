package spring;


import annotationDriver.config.MainConfigOfLifeCycle;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.bean.Car1;


public class Main {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MainConfig.class);
        Car1 c = (Car1) applicationContext.getBean("car1");
        applicationContext.getBean("dataSource");
        c.init();
    }
}
