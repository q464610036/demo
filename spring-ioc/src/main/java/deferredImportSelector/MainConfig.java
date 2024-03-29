package deferredImportSelector;

import deferredImportSelector.bean.Cat;
import org.springframework.context.annotation.*;

@ComponentScan("deferredImportSelector.bean")
@Configuration
@Import(MyDeferredImportSelector.class)
public class MainConfig {

}
