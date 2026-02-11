package com.example.legend;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.legend.common.enums.ResultCode;
import com.example.legend.common.util.*;
import com.example.legend.common.vo.R;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
//                    R<Boolean> result = aiOCR();
                    R<Boolean> result = baiduOCR();
                    if (R.isNotSuccess(result)) {
                        Thread.sleep(10000);
                    } else {
                        if (result.getData()) {
                            QQSMSUtils.send(LegendMonitorMain.emailList, "高级物品掉落告知", "");
                            Thread.sleep(1000 * 60 * 5);
                        } else {
                            Thread.sleep(1000 * 60 * 2);
                        }
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static R<Boolean> aiOCR() throws FileNotFoundException, InterruptedException {
        System.out.println(DateUtil.format(new Date())+"识别中...");
        String text = AiOcrUtil.ocr(path);
        String[] list = text.split("\n");
        String message = "";
        for (String str : list) {
            for (String param : LegendMonitorMain.paramList) {
                if (str.contains(param)) {
                    System.out.println(DateUtil.format(new Date())+"物品识图命中：" + str);
                    message += str + "\n";
                    break;
                }
            }
        }
        if (StringUtil.isEmpty(message)) {
            System.out.println(DateUtil.format(new Date())+"未命中");
            return R.data(false);
        } else {
            System.out.println(DateUtil.format(new Date())+"命中");
            return R.data(true);
        }
    }

    final static String APP_ID = "34800730";
    final static String API_KEY = "BA7T7bAUU6Tn9GvXgvHb5NFZ";
    final static String SECRET_KEY = "jwYe8p2D4V8A9fFxA0GjtpOqKR0pIrQE";
    public static R<Boolean> baiduOCR() {
        //百度ORC识别
        BaiDuOcrUtil baiDuOcrUtil = new BaiDuOcrUtil(APP_ID, API_KEY, SECRET_KEY);
        String jsonStr = baiDuOcrUtil.general(path);
        log.info("识别结果："+jsonStr);
        if (StringUtil.isEmpty(jsonStr)) {
            log.info("识图失败");
            return R.fail(ResultCode.INTERFACE_INNER_INVOKE_ERROR);
        } else {
            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            JSONArray wordsResultList = JSONObject.parseArray(jsonObject.getString("words_result"));
            for (int i = 0; i < wordsResultList.size();i++){
                JSONObject jsonObject1 = wordsResultList.getJSONObject(i);
                String text = jsonObject1.getString("words");
                for (String param : LegendMonitorMain.paramList) {
                    if (text.contains(param)) {
                        System.out.println("识图命中："+param);
                        return R.data(true);
                    }
                }
            }
        }
        return R.data(false);
    }
}
