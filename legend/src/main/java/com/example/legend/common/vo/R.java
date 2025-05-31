package com.example.legend.common.vo;

import com.example.legend.common.enums.ResultCode;
import com.example.legend.common.util.IResultCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code;
    private T data;
    private String msg;
    private Long timeStamp;

    private R(IResultCode resultCode) {
        this(resultCode, (T) null, resultCode.getMessage());
    }

    private R(IResultCode resultCode, String msg) {
        this(resultCode, (T) null, msg);
    }

    private R(IResultCode resultCode, T data) {
        this(resultCode, data, resultCode.getMessage());
    }

    private R(IResultCode resultCode, T data, String msg) {
        this(resultCode.getCode(), data, msg);
    }

    private R(int code, T data, String msg) {
        this.timeStamp = Calendar.getInstance().getTimeInMillis();
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static boolean isSuccess(@Nullable R<?> result) {
        return (Boolean)Optional.ofNullable(result).map((x) -> {
            return nullSafeEquals(ResultCode.SUCCESS.getCode(), x.code);
        }).orElse(Boolean.FALSE);
    }

    public static boolean isNotSuccess(@Nullable R<?> result) {
        return !isSuccess(result);
    }

    public static <T> R<T> data(T data) {
        return data(data, "操作成功");
    }

    public static <T> R<T> data(T data, String msg) {
        return data(ResultCode.SUCCESS.getCode(), data, msg);
    }

    public static <T> R<T> data(int code, T data, String msg) {
        return new R(code, data, data == null ? "暂无承载数据" : msg);
    }

    public static <T> R<T> success(String msg) {
        return new R(ResultCode.SUCCESS, msg);
    }

    public static <T> R<T> success(IResultCode resultCode) {
        return new R(resultCode);
    }

    public static <T> R<T> success(IResultCode resultCode, String msg) {
        return new R(resultCode, msg);
    }

    public static <T> R<T> fail(String msg) {
        return new R(ResultCode.SERVER_ERROR, msg);
    }

    public static <T> R<T> fail(int code, String msg) {
        return new R(code, (Object)null, msg);
    }

    public static <T> R<T> fail(IResultCode resultCode) {
        return new R(resultCode);
    }

    public static <T> R<T> fail(IResultCode resultCode, String msg) {
        return new R(resultCode, msg);
    }

    public static <T> R<T> status(boolean flag) {
        return flag ? success("操作成功") : fail("操作失败");
    }

    public static boolean nullSafeEquals(@Nullable Object o1, @Nullable Object o2) {
        if (o1 == o2) {
            return true;
        } else if (o1 != null && o2 != null) {
            if (o1.equals(o2)) {
                return true;
            } else {
                return o1.getClass().isArray() && o2.getClass().isArray() ? arrayEquals(o1, o2) : false;
            }
        } else {
            return false;
        }
    }

    private static boolean arrayEquals(Object o1, Object o2) {
        if (o1 instanceof Object[] && o2 instanceof Object[]) {
            return Arrays.equals((Object[])((Object[])o1), (Object[])((Object[])o2));
        } else if (o1 instanceof boolean[] && o2 instanceof boolean[]) {
            return Arrays.equals((boolean[])((boolean[])o1), (boolean[])((boolean[])o2));
        } else if (o1 instanceof byte[] && o2 instanceof byte[]) {
            return Arrays.equals((byte[])((byte[])o1), (byte[])((byte[])o2));
        } else if (o1 instanceof char[] && o2 instanceof char[]) {
            return Arrays.equals((char[])((char[])o1), (char[])((char[])o2));
        } else if (o1 instanceof double[] && o2 instanceof double[]) {
            return Arrays.equals((double[])((double[])o1), (double[])((double[])o2));
        } else if (o1 instanceof float[] && o2 instanceof float[]) {
            return Arrays.equals((float[])((float[])o1), (float[])((float[])o2));
        } else if (o1 instanceof int[] && o2 instanceof int[]) {
            return Arrays.equals((int[])((int[])o1), (int[])((int[])o2));
        } else if (o1 instanceof long[] && o2 instanceof long[]) {
            return Arrays.equals((long[])((long[])o1), (long[])((long[])o2));
        } else {
            return o1 instanceof short[] && o2 instanceof short[] ? Arrays.equals((short[])((short[])o1), (short[])((short[])o2)) : false;
        }
    }

    public int getCode() {
        return this.code;
    }

    public T getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public Long getTimeStamp() {
        return this.timeStamp;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    public void setTimeStamp(final Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String toString() {
        return "R(code=" + this.getCode() + ", data=" + this.getData() + ", msg=" + this.getMsg() + ", timeStamp=" + this.getTimeStamp() + ")";
    }

    public R() {
        this.timeStamp = Calendar.getInstance().getTimeInMillis();
    }
}
