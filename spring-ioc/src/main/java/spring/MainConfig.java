package spring;

import annotationDriver.bean.Car;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.StringValueResolver;
import spring.config.JdbcConfig;

//扫描bean包
@ComponentScan("spring.bean")
@Configuration
@Import(JdbcConfig.class)
public class MainConfig{


}
