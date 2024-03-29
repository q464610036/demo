package com.threadPool;

import java.util.Set;
import java.util.concurrent.*;

public class CyclicBarrierTest2 {
    //保存每个学生的平均成绩
    private static ConcurrentHashMap<String, Integer> map=new ConcurrentHashMap<String,Integer>();

    private static ExecutorService threadPool= Executors.newFixedThreadPool(3);



    public static void main(String[] args) {
        CyclicBarrier cb=new CyclicBarrier(3,()->{
            //3个线程都做完了，计算平均成绩
            int result=0;
            Set<String> set = map.keySet();
            for(String s:set){
                result+=map.get(s);
            }
            System.out.println("三人平均成绩为:"+(result/3)+"分");
        });
        //执行线程
        for(int i=0;i<3;i++){
            threadPool.execute(new Runnable(){
                @Override
                public void run() {
                    //获取学生平均成绩
                    int score=(int)(Math.random()*40+60);
                    map.put(Thread.currentThread().getName(), score);
                    System.out.println(Thread.currentThread().getName()
                            +"同学的平均成绩为："+score);
                    try {
                        //执行完运行await(),等待所有学生平均成绩都计算完毕
                        cb.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
