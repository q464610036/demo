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
public class ResultUtil {
    @ApiModelProperty("代码")
    private Integer Code;

    @ApiModelProperty("错误消息")
    private String msg;

    @ApiModelProperty("状态")
    private Boolean status;

    @ApiModelProperty("数据")
    private Object data;

    public static Object isSuccess(Object data){
        return new ResultUtil(10000, "操作成功",  true,  data);
    }

    public static Object isSuccess(String msg, Object data){
        return new ResultUtil(10000, msg,  true,  data);
    }

    public static Object isFail(Integer Code, String msg){
        return new ResultUtil(Code, msg,  false,  null);
    }
}
