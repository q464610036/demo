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
        // 获取当前工作目录的路径
        String currentDir = System.getProperty("user.dir");
        // 打印当前工作目录的路径
        System.out.println("当前工作目录是: " + currentDir);
        Properties properties=new Properties();
        try {
            properties.load(new InputStreamReader(LegendMonitorMain.class.getResourceAsStream("/main.properties"), "UTF-8"));
//            properties.load(new InputStreamReader(new FileInputStream("main.properties"), "UTF-8"));
            System.out.println("email="+properties.getProperty("email"));
            String[] emails = properties.getProperty("email").split(",");
            for (String email : emails) {
                if (!StringUtil.isEmpty(email)) {
                    emailList.add(email);
                }
            }
//            System.out.println("param="+properties.getProperty("param"));
            String[] params = properties.getProperty("param").split(",");
            for (String param : params) {
                if (!StringUtil.isEmpty(param)) {
                    paramList.add(param);
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
