package com.cn.module.monitor;

import java.util.ArrayList;

public class MonitorMain {
    final static ArrayList<String> emailList = new ArrayList<>();
    final static String rootFolder = "C://lineage2Log";

    static {
        emailList.add("464610036@qq.com");
        emailList.add("38755065@qq.com");
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new DiLongMonitor());
        thread1.start();

        Thread thread2 = new Thread(new HuoLongMonitor());
        thread2.start();
    }
}
