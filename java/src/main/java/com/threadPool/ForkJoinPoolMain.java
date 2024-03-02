package com.threadPool;

import java.util.concurrent.*;

public class ForkJoinPoolMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool(10);
        SumTask sumTask = new SumTask(1, 10000);
        ForkJoinTask<Long> forkJoinTask = pool.submit(sumTask);
        if (forkJoinTask.isCompletedAbnormally()) {
            //异常
            System.out.println(forkJoinTask.getException());
        }
        System.out.println(forkJoinTask.get());
    }
}
