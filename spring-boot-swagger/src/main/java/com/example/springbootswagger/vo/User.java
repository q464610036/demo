package com.example.springbootswagger.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    @ApiModelProperty("用户id")
    private Integer userId;
    @ApiModelProperty("用户名称")
    private String name;
    @ApiModelProperty("日期")
    private Date date;
}
