package circularDependency;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import spring.config.JdbcConfig;

//扫描bean包
@ComponentScan("circularDependency.bean")
@Configuration
public class MainConfig{

}
