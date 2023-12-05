package com.cn.module.monitor;


import com.cn.common.util.StringUtil;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class MonitorMain {
    final static ArrayList<String> emailList = new ArrayList<>();
    final static String rootFolder = "C://lineage2Log";
    final static Double similarity = 0.9D;

    static {
//        emailList.add("464610036@qq.com");
////        emailList.add("38755065@qq.com");
//        emailList.add("344542878@qq.com");
        InputStream inputStream = MonitorMain.class.getResourceAsStream("/main.properties");
        Properties properties=new Properties();
        try {
            properties.load(inputStream);
            System.out.println("email="+properties.getProperty("email"));
            String[] emails = properties.getProperty("email").split(",");
            for (String email : emails) {
                if (!StringUtil.isEmpty(email)) {
                    emailList.add(email);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new DiLongMonitor());
        thread1.start();

        Thread thread2 = new Thread(new HuoLongMonitor());
        thread2.start();
    }
}
