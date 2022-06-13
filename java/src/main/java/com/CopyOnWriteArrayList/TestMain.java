package com.CopyOnWriteArrayList;

import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author 陈孟飞
 * @date 2021/8/4
 */
public class TestMain {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList();
        list.add("add");
        list.add("del");
        list.add("udate");
        list.add("query");
        for (String s : list) {
            if (s.equals("del")) {
                list.remove(s);
            } else {
                System.out.println(s);
            }
        }

    }
}
