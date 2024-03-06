package circularDependency.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class Man {

    @Autowired
    private Woman woman;

    public Man() {

    }

    public void init() {

    }

    public void destory() {

    }

}
