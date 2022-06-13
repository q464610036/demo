package com.lambda;

import lombok.Data;

/**
 * @author 陈孟飞
 * @date 2021/2/19
 */
@Data
public class PeopleVO {

    private String name;
    private Integer age;

    private Integer weight;

    public PeopleVO(String name, Integer age, Integer weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public PeopleVO() {

    }
}
