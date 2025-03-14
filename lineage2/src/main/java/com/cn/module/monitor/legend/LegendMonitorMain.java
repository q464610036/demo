package com.cn.module.monitor.legend;


import com.cn.common.util.StringUtil;
import com.cn.module.monitor.lineage2.DiLongMonitor;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class LegendMonitorMain {
    final static ArrayList<String> emailList = new ArrayList<>();
    final static String rootFolder = "C://legendLog";
    final static Double similarity = 0.9D;

    static {
        InputStream inputStream = LegendMonitorMain.class.getResourceAsStream("/main.properties");
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
        Thread thread1 = new Thread(new MessageMonitor());
        thread1.start();
    }
}
