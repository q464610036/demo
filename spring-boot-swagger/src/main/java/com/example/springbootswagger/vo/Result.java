package com.example.springbootswagger.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    @ApiModelProperty("代码")
    private Integer Code;

    @ApiModelProperty("错误消息")
    private String msg;

    @ApiModelProperty("状态")
    private Boolean status;

    @ApiModelProperty("数据")
    private T data;

    public static<T> Result<T> isSuccess(T data){
        return new Result<T>(10000, "操作成功",  true,  data);
    }

    public static<T> Result<T> isSuccess(String msg, T data){
        return new Result<T>(10000, msg,  true,  data);
    }

    public static<T> Result<T> isFail(Integer Code, String msg){
        return new Result<T>(Code, msg,  false,  null);
    }
}
