package com.example.legend;



import com.example.legend.common.util.StringUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;

public class LegendMonitorMain {
    public final static ArrayList<String> emailList = new ArrayList<>();
    public final static ArrayList<String> paramList = new ArrayList<>();
    final static String rootFolder = "C://legendLog";
    final static Double similarity = 0.9D;

    static {
        Properties properties=new Properties();
        try {
            properties.load(new InputStreamReader(new FileInputStream("main.properties"), "UTF-8"));
            System.out.println("加载外部配置文件...");
        } catch (IOException e) {
            try {
                //读不到就读项目中的配置文件
                properties.load(new InputStreamReader(LegendMonitorMain.class.getResourceAsStream("/main.properties"), "UTF-8"));
                System.out.println("加载项目配置文件...");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        String[] emails = properties.getProperty("email").split(",");
        System.out.println("emails="+properties.getProperty("email"));
        for (String email : emails) {
            if (!StringUtil.isEmpty(email)) {
                emailList.add(email);
            }
        }
        String[] params = properties.getProperty("param").split(",");
        for (String param : params) {
            if (!StringUtil.isEmpty(param)) {
                paramList.add(param);
            }
        }
        MessageMonitor.APP_ID = properties.getProperty("APP_ID");
        System.out.println("APP_ID="+MessageMonitor.APP_ID);
        MessageMonitor.API_KEY = properties.getProperty("API_KEY");
        System.out.println("API_KEY="+MessageMonitor.API_KEY);
        MessageMonitor.SECRET_KEY = properties.getProperty("SECRET_KEY");
        System.out.println("SECRET_KEY="+MessageMonitor.SECRET_KEY);
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new MessageMonitor());
        thread1.start();
    }
}
