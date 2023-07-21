package com.cn.module.monitor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cn.common.enums.ResultCode;
import com.cn.common.util.*;
import com.cn.common.vo.R;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DiLongMonitor implements Runnable {
    private static Logger log = Logger.getLogger(DiLongMonitor.class);

    final static String APP_ID = "34800730";
    final static String API_KEY = "BA7T7bAUU6Tn9GvXgvHb5NFZ";
    final static String SECRET_KEY = "jwYe8p2D4V8A9fFxA0GjtpOqKR0pIrQE";
    final static String rootFolder = MonitorMain.rootFolder+"/log_dilong";
    final static String timePath = rootFolder +"/time.txt";
    final static String historyPath = rootFolder + "/temp_bak.jpg";
    final static String path = rootFolder + "/temp.jpg";
    final static int x1 = 0;
    final static int y1 = 0;
    final static int x2 = 380;
    final static int y2 = 440;
    final static ArrayList<String> paramList = new ArrayList<>();
    static{
        paramList.add("传送石");
        paramList.add("已经有人进入");
    }
    @SneakyThrows
    @Override
    public void run() {
        while(true) {
            try {
                //获取开始时间
                Date startDate = TimeUtil.readStartTime(timePath);
                if(System.currentTimeMillis() > startDate.getTime() ){
                    //截屏
                    MonitorManager.createImage(path, historyPath, x1, y1, x2, y2);
                    //图片相似度对比
                    Double similarity = MonitorManager.contrast(path, historyPath);
                    if (similarity == 1D) {
                        System.out.println(DateUtil.format(new Date())+" 地龙前后图片过于相似，相似度："+similarity);
                        Thread.sleep(10000);
                    } else {
                        System.out.println(DateUtil.format(new Date())+" 地龙前后图片不相似，相似度："+similarity);
                        //图片识别
                        R<Boolean> result = baiduOCR();
                        if (R.isNotSuccess(result)) {
                            Thread.sleep(10000);
                        } else {
                            if (result.getData()) {
                                QQSMSUtils.send(MonitorMain.emailList,"狩猎首领刷新告知", "地龙已出现");
                                //开始时间延迟5天
                                startDate = DateUtil.addDate(new Date(), 5, Calendar.DATE);
                                //写入时间
                                FileUtil.writerLine(timePath, DateUtil.format(startDate));
                            } else {
                                Thread.sleep(1000 * 60 * 10 );
                            }
                        }
                    }
                } else {
                    //开始时间未到，休眠10秒继续
                    System.out.println(DateUtil.format(new Date())+" 地龙未开始检测，开始时间:"+DateUtil.format(startDate));
                    Thread.sleep(10000);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 地龙监控识别
     * @return
     */
    public static R<Boolean> baiduOCR() {
        //百度ORC识别
        BaiDuOcrUtil baiDuOcrUtil = new BaiDuOcrUtil(APP_ID, API_KEY, SECRET_KEY);
        String jsonStr = baiDuOcrUtil.general(path);
        log.info("地龙识别结果："+jsonStr);
        if (StringUtil.isEmpty(jsonStr)) {
            log.info("地龙识图失败");
            return R.fail(ResultCode.INTERFACE_INNER_INVOKE_ERROR);
        } else {
            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            JSONArray wordsResultList = JSONObject.parseArray(jsonObject.getString("words_result"));
            for (int i = 0; i < wordsResultList.size();i++){
                JSONObject jsonObject1 = wordsResultList.getJSONObject(i);
                String text = jsonObject1.getString("words");
                for (String param : paramList) {
                    if (text.contains(param)) {
                        log.info("地龙识图命中："+param);
                        return R.data(true);
                    }
                }
            }
        }
        return R.data(false);
    }

}
