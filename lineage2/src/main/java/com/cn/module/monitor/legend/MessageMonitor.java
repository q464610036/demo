package com.cn.module.monitor.legend;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cn.common.enums.ResultCode;
import com.cn.common.util.*;
import com.cn.common.vo.R;
import com.cn.module.monitor.legend.LegendMonitorMain;
import com.cn.module.monitor.MonitorManager;
import com.cn.module.monitor.lineage2.Lineage2MonitorMain;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 聊天窗口监视器
 */
public class MessageMonitor implements Runnable {
    private static Logger log = Logger.getLogger(MessageMonitor.class);

    final static String rootFolder = LegendMonitorMain.rootFolder+"/log_message";
    final static String timePath = rootFolder +"/time.txt";
    final static String historyPath = rootFolder + "/temp_bak.jpg";
    final static String path = rootFolder + "/temp.jpg";
    final static int x1 = 200;
    final static int y1 = 0;
    final static int x2 = 800;
    final static int y2 = 830;
    final static ArrayList<String> paramList = new ArrayList<>();
    static{
        paramList.add("发现物品");
    }
    @SneakyThrows
    @Override
    public void run() {
        while(true) {
            try {
                //截屏
                MonitorManager.createImage(path, historyPath, x1, y1, x2, y2);
                //图片相似度对比
                Double similarity = MonitorManager.contrast(path, historyPath);
                if (similarity >= LegendMonitorMain.similarity) {
                    System.out.println(DateUtil.format(new Date())+"图片过于相似不调用识别，相似度："+similarity);
                    Thread.sleep(10000);
                } else {
                    //图片识别
                    String text = AiOcrUtil.ocr(path);
                    //                    System.out.println("text:"+text);
                    String[] list = text.split("\n");
                    String message = "";
                    for (String str : list) {
                        for (String param : paramList) {
                            if (str.contains(param)) {
                                System.out.println(DateUtil.format(new Date())+"物品识图命中：" + str);
                                message += str + "\n";
                                break;
                            }
                        }
                    }
                    if (StringUtil.isEmpty(message)) {
                        System.out.println(DateUtil.format(new Date())+"未命中");
                    } else {
                        //发送邮件
                        QQSMSUtils.send(LegendMonitorMain.emailList, "高级物品掉落告知", message);
                        //写入时间
                        Thread.sleep(1000 * 60 * 5);
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
