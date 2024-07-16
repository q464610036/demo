package helloword.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class Employee {
    private Integer userId;
    private String name;
    private Date date;
}
