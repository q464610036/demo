package com.lambda;

import lombok.Data;

/**
 * @author 陈孟飞
 * @date 2021/2/19
 */
@Data
public class StudentVO extends PeopleVO {

    private String className;

    public StudentVO(String name, Integer age, String className, Integer weight) {
        super(name, age, weight);
        this.className = className;
    }

    public StudentVO(String name, Integer age, Integer weight) {
        super(name, age, weight);
    }

    public StudentVO(Integer age) {
        this.setAge(age);
    }

    public StudentVO() {

    }

    public boolean test(Integer a, Integer b) {
        return true;
    }
}
