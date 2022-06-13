package com.timedemo;

/**
 * @author 陈孟飞
 * @date 2021/8/30
 */
public class StringUtil {
    public static String appendString(int count){
        String str = "";
        for(int i=0;i<count;i++){
            str+= "1";
        }
        return str;
    }

    public static StringBuilder appendStringBuilder(int count){
        StringBuilder str = new StringBuilder("");
        for(int i=0;i<count;i++){
            str.append("1");
        }
        return str;
    }

    public static void main(String[] args) {
        int count = 1000;
        Long t = System.nanoTime();
        appendString(count);
        System.out.println(""+(System.nanoTime() - t));
        t = System.nanoTime();
        appendStringBuilder(count);
        System.out.println(""+(System.nanoTime() - t));
        t = System.nanoTime();
    }
}
