package com.example.springsecurity.common.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultUtil {
    private Integer Code;

    private String msg;

    private Boolean status;

    private Object data;

    public static ResultUtil isSuccess(Object data){
        return new ResultUtil(10000, "操作成功",  true,  data);
    }

    public static ResultUtil isSuccess(String msg, Object data){
        return new ResultUtil(10000, msg,  true,  data);
    }

    public static ResultUtil isFail(Integer Code, String msg){
        return new ResultUtil(Code, msg,  false,  null);
    }
}
