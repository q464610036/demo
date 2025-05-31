package com.cn.module.monitor.lineage2;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cn.common.enums.ResultCode;
import com.cn.common.util.*;
import com.cn.common.vo.R;
import com.cn.module.monitor.MonitorManager;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ShopMonitor implements Runnable {
    private static Logger log = Logger.getLogger(ShopMonitor.class);

    final static String APP_ID = "34800730";
    final static String API_KEY = "BA7T7bAUU6Tn9GvXgvHb5NFZ";
    final static String SECRET_KEY = "jwYe8p2D4V8A9fFxA0GjtpOqKR0pIrQE";
    final static String rootFolder = Lineage2MonitorMain.rootFolder+"/log_shop";
    final static String timePath = rootFolder +"/time.txt";
    final static String historyPath = rootFolder + "/temp_bak.jpg";
    final static String path = rootFolder + "/temp.jpg";
    final static int x1 = 1300;
    final static int y1 = 80;
    final static int x2 = 1500;
    final static int y2 = 250;
    final static ArrayList<String> paramList = new ArrayList<>();
    static{
        paramList.add("树妖");
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
                    if (similarity >= Lineage2MonitorMain.similarity) {
                        System.out.println(DateUtil.format(new Date())+" 前后图片过于相似，相似度："+similarity);
                        Thread.sleep(10000);
                    } else {
                        System.out.println(DateUtil.format(new Date())+" 前后图片不相似，相似度："+similarity);
                        QQSMSUtils.send(Lineage2MonitorMain.emailList,"商店刷新告知", "", path);
                        Thread.sleep(1000 * 60 * 10);
                    }
                } else {
                    //开始时间未到，休眠10秒继续
                    System.out.println(DateUtil.format(new Date())+" 未开始检测，开始时间:"+DateUtil.format(startDate));
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
                for (String param : paramList) {
                    if (text.contains(param)) {
                        log.info("识图命中："+param);
                        return R.data(true);
                    }
                }
            }
        }
        return R.data(false);
    }

}
