package com.hashMap;

import java.util.HashMap;

/**
 * @author 陈孟飞
 * @date 2021/10/8
 */
public class ComputeIfAbsent {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        // java8之前。从map中根据key获取value操作可能会有下面的操作
//        Object key = map.get("key");
//        if (key == null) {
//            key = new Object();
//            map.put("key", key);
//        }
        // java8之后。上面的操作可以简化为一行，若key对应的value为空，会将第二个参数的返回值存入并返回
        Object key2 = map.computeIfAbsent("key", k -> new Object());
        System.out.println("key2:"+key2);
        System.out.println("map:"+map);
    }
}
