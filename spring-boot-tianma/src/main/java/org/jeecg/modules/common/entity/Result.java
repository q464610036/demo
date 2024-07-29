package org.jeecg.modules.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: tmos
 * @description:
 * @author: ZhangZB
 * @create: 2024-07-07 22:16
 **/
@Data
public class Result<T> implements Serializable {
    private boolean success = true;

    private String message = "";

    private Integer code = 0;

    private T result;

    private long timestamp = System.currentTimeMillis();

    public static<T> Result<T> ok(){
        Result<T> r = new Result<>();
        r.setSuccess(true);
        r.setCode(200);
        return r;
    }

    public static<T> Result<T> ok(String msg){
        Result<T> r = new Result<>();
        r.setSuccess(true);
        r.setCode(200);
        r.setResult((T) msg);
        r.setMessage(msg);
        return r;
    }

    public static<T> Result<T> ok(T data){
        Result<T> r = new Result<>();
        r.setSuccess(true);
        r.setCode(200);
        r.setResult(data);
        return r;
    }

    public static<T> Result<T> error(String msg, T data){
        Result<T> r = new Result<>();
        r.setSuccess(false);
        r.setCode(500);
        r.setMessage(msg);
        r.setResult(data);
        return r;
    }

    public static<T> Result<T> error(String msg){
        return error(500, msg);
    }

    public static<T> Result<T> error(int code, String msg){
        Result<T> r = new Result<>();
        r.setSuccess(false);
        r.setCode(code);
        r.setMessage(msg);
        return r;
    }
}
