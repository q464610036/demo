package com.test;

/**
 * @author 陈孟飞
 * @date 2022/6/2
 */
public class Test {
    static boolean flag = false;
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("wait......");
                while(true){
                    if (flag) {
                        break;
                    }
                }
                System.out.println("执行完毕");
            }
        }).start();

        Thread.sleep(1000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                flag = true;
                System.out.println("set:"+flag);
            }
        }).start();
    }

}
