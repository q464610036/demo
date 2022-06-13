package com.lambda;

/**
 * @author 陈孟飞
 * @date 2021/2/19
 */
@FunctionalInterface
public interface MyPredicate<T> {
    boolean test(T t);
}
