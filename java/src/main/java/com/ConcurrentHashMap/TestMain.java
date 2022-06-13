package com.ConcurrentHashMap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 陈孟飞
 * @date 2021/7/23
 */
public class TestMain {
    public static void main(String[] args) {
        ConcurrentHashMap c = new ConcurrentHashMap();
        c.put("1", 1);
        c.put("2", 1);
        c.get("1");
    }
}
