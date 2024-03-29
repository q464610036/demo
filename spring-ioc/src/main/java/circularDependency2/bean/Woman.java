package circularDependency2.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class Woman {
    private Man man;

    @Autowired
    //写在方法里也可以
    public Woman(@Lazy Man man) {
        this.man = man;
    }
}
