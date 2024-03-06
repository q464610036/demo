package circularDependency.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class Woman {
    @Autowired
    private Man man;

    public Woman() {

    }

    public void init() {

    }

    public void destory() {

    }

}
