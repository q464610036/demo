package com.cn.module.monitor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cn.common.enums.ResultCode;
import com.cn.common.util.*;
import com.cn.common.vo.R;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HuoLongMonitor implements Runnable {
    private static Logger log = Logger.getLogger(HuoLongMonitor.class);

    final static String APP_ID = "";
    final static String API_KEY = "GGWReTqUxCtmBHbT1lFbFKmE";
    final static String SECRET_KEY = "RHR6NdkxjzbZNB99aWGP6hilV9lzap7l";
    final static String rootFolder = MonitorMain.rootFolder + "/log_huolong";
    final static String timePath = rootFolder +"/time.txt";
    final static String historyPath = rootFolder + "/temp_bak.jpg";
    final static String path = rootFolder + "/temp.jpg";
    final static int x1 = 0;
    final static int y1 = 430;
    final static int x2 = 560;
    final static int y2 = 776;
    final static String param = "火山";

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            try {
                //获取开始时间
                Date startDate = TimeUtil.readStartTime(timePath);
                if (System.currentTimeMillis() > startDate.getTime()) {
                    //截屏
                    MonitorManager.createImage(path, historyPath, x1, y1, x2, y2);
                    //图片相似度对比
                    Double similarity = MonitorManager.contrast(path, historyPath);
                    if (similarity == 1D) {
                        System.out.println(DateUtil.format(new Date())+" 火龙前后图片过于相似，相似度："+similarity);
                        Thread.sleep(10000);
                    } else {
                        System.out.println(DateUtil.format(new Date())+" 火龙前后图片不相似，相似度："+similarity);
                        //图片识别
                        R<Boolean> result = baiduOCR();
                        if (R.isNotSuccess(result)) {
                            Thread.sleep(10000);
                        } else {
                            if (result.getData()) {
                                QQSMSUtils.send(MonitorMain.emailList, "狩猎首领刷新告知", "火龙已出现");
                                //开始时间延迟6天
                                startDate = DateUtil.addDate(new Date(), 6, Calendar.DATE);
                                //写入时间
                                FileUtil.writerLine(timePath, DateUtil.format(startDate));
                            } else {
                                Thread.sleep(1000 * 60 * 10);
                            }
                        }
                    }
                } else {
                    //开始时间未到，休眠10秒继续
                    System.out.println(DateUtil.format(new Date()) + " 火龙未开始检测，开始时间:" + DateUtil.format(startDate));
                    Thread.sleep(10000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 地龙监控识别
     *
     * @return
     */
    public static R<Boolean> baiduOCR() {
        //百度ORC识别
        BaiDuOcrUtil baiDuOcrUtil = new BaiDuOcrUtil(APP_ID, API_KEY, SECRET_KEY);
        String jsonStr = baiDuOcrUtil.general(path);
        log.info("火龙识别结果：" + jsonStr);
        if (StringUtil.isEmpty(jsonStr)) {
            log.info("火龙识图失败");
            return R.fail(ResultCode.INTERFACE_INNER_INVOKE_ERROR);
        } else {
            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            JSONArray wordsResultList = JSONObject.parseArray(jsonObject.getString("words_result"));
            boolean flag = false;
            for (int i = 0; i < wordsResultList.size(); i++) {
                JSONObject jsonObject1 = wordsResultList.getJSONObject(i);
                String text = jsonObject1.getString("words");
                if (text.contains(param)) {
                    flag = true;
                }
            }
            //返回的信息没有火山，则表示识别命中
            if (!flag) {
                log.info("火龙识别命中");
                return R.data(true);
            }
        }
        return R.data(false);
    }
}
