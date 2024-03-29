package circularDependency2;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//扫描bean包
@ComponentScan("circularDependency2.bean")
@Configuration
public class MainConfig{

}
