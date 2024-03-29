package helloword;

import helloword.listener.MyEvent;
import helloword.listener.MyListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

//标注主程序，说用这是一个spring boot应用
@SpringBootApplication
public class HelloWordApplication {

    public static void main(String[] args) {
        //启动spring boot应用
        ConfigurableApplicationContext applicationContext = SpringApplication.run(HelloWordApplication.class, args);
        applicationContext.addApplicationListener(new MyListener());
    }
}
