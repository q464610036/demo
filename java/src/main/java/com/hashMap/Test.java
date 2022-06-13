package com.hashMap;

import java.time.LocalTime;
import java.util.HashMap;

/**
 * @author 陈孟飞
 * @date 2021/8/2
 */
public class Test {

    public static void main(String[] args) {
//        HashMap a = new HashMap();
//        a.put(new MyKey(1), 1);
//        a.put(new MyKey(2), 2);
//        a.put(new MyKey(3), 3);
//        a.put(new MyKey(4), 4);
//        a.put(new MyKey(5), 5);
//        a.put(new MyKey(6), 6);
//        a.put(new MyKey(7), 7);
//        a.put(new MyKey(8), 8);
//        test();
        test2();
    }

    public static void test() {
        String[] a = new String[]{"a", "b"};
        for (String[] b = a; ; ) {
            int i = 0;
            String str = b[i];
            System.out.println("str:" + str);
            if (i == a.length - 1) {
                break;
            } else {
                i++;
            }
        }
    }

    public static void test2(){
        LocalTime rightNow=LocalTime.now();
        LocalTime bedTime=LocalTime.of(22, 10);
        System.out.println(rightNow);
        System.out.println(bedTime);
        LocalTime wakeUp=bedTime.plusHours(10);
        bedTime.plusMinutes(10);
        bedTime.plusSeconds(10);
        bedTime.minusHours(1);
        bedTime.minusMinutes(1);
        bedTime.minusSeconds(1);

        LocalTime time=LocalTime.parse("10:10:10");
        LocalTime time1=bedTime.withHour(12);
        LocalTime time2=bedTime.withMinute(12);
        LocalTime time3=bedTime.withSecond(12);
        System.out.println(time1);
        System.out.println(time2);
        System.out.println(time3);

        long hour=rightNow.getHour();
        long minute=rightNow.getMinute();
        long second=rightNow.getSecond();
        System.out.println(hour+"  "+minute+"  "+second);

        System.out.println(rightNow.isBefore(bedTime));
    }


}
