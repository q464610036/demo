package com.thread;

import java.util.concurrent.Callable;

/**
 * @author 陈孟飞
 * @date 2021/8/6
 */
public class Callable1 implements Callable {
    @Override
    public String call() throws Exception {
        return "111";
    }
}
