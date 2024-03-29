package circularDependency2.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class Man {
    private Woman woman;

    @Autowired
    @Lazy
    public Man(Woman woman) {
        this.woman = woman;
    }
}
