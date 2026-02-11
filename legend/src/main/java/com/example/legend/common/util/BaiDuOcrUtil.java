package com.example.legend.common.util;

import com.baidu.aip.ocr.AipOcr;
import com.baidu.aip.util.Base64Util;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class BaiDuOcrUtil {
    //设置APPID/AK/SK
    public String APP_ID;
    public String API_KEY;
    public String SECRET_KEY;
    public AipOcr client;
    public BaiDuOcrUtil(String APP_ID, String API_KEY, String SECRET_KEY){
        this.APP_ID = APP_ID;
        this.API_KEY = API_KEY;
        this.SECRET_KEY = SECRET_KEY;
    }

    public void connent(){
        client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
    }

    /**
     * 通用文字识别
     * @param path
     * @return
     */
    public String basicGeneral(String path) {
        if(client== null){
            connent();
        }
        // 初始化一个AipOcr
        HashMap<String, String> options = new HashMap<String, String>();
        /*
        options.put("recognize_granularity", "big");
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("vertexes_location", "true");
        options.put("probability", "true");
        */
        options.put("recognize_granularity", "small");
        options.put("detect_direction", "true");
        options.put("vertexes_location", "true");
        options.put("probability", "true");


        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 调用接口
//        String path = "D://11.png";
        JSONObject jsonObject = client.basicGeneral(path, options);//基础
//        JSONObject jsonObject = client.general(path, options);//返回坐标
//        JSONObject jsonObject = client.accurateGeneral(path, options);//返回高精度坐标
//        System.out.println(jsonObject.toString(2));
        return jsonObject.toString();
    }


    public String general(String path) throws IOException {
        if(client== null){
            connent();
        }
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("recognize_granularity", "big");
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("vertexes_location", "true");
        options.put("probability", "true");

        byte[] bytes = Files.readAllBytes(Paths.get(path));
        // 通用文字识别（含位置信息版）, 图片参数为远程url图片
        JSONObject jsonObject = client.general(bytes, options);
        String str = jsonObject.toString();
        return str;
    }

    /**
     *  idCardSide front=正面，back=背面
     * @param path
     * @return
     */
    public String sample(String path, String idCardSide) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        options.put("detect_risk", "false");
        // 参数为本地路径
        JSONObject res = client.idcard(path, idCardSide, options);
        return res.toString(2);
    }


//    public static void main(String[] args) {
//        String APP_ID = "34800730";
//        String API_KEY = "BA7T7bAUU6Tn9GvXgvHb5NFZ";
//        String SECRET_KEY = "jwYe8p2D4V8A9fFxA0GjtpOqKR0pIrQE";
//        //百度ORC识别
//        BaiDuOcrUtil baiDuOcrUtil = new BaiDuOcrUtil(APP_ID, API_KEY, SECRET_KEY);
//        baiDuOcrUtil.connent();
//        // 初始化一个AipOcr
//        HashMap<String, String> options = new HashMap<String, String>();
//        options.put("recognize_granularity","big");
//        options.put("probability","false");
//        options.put("detect_direction","true");
//        String path = "D://test/1.png";
//        File file = new File(path);
//        JSONObject jsonObject = baiDuOcrUtil.client.handwriting(path, options);
//        System.out.println(file.getName()+":"+jsonObject.toString());
//        path = "D://test/2.png";
//        file = new File(path);
//        jsonObject = baiDuOcrUtil.client.handwriting(path, options);
//        System.out.println(file.getName()+":"+jsonObject.toString());
//        path = "D://test/3.png";
//        file = new File(path);
//        jsonObject = baiDuOcrUtil.client.handwriting(path, options);
//        System.out.println(file.getName()+":"+jsonObject.toString());
//    }
}

