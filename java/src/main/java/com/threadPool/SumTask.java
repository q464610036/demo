package com.threadPool;

import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Long> {

    private final int begin;
    private final int end;

    public SumTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }


    @Override
    protected Long compute() {
        long sum = 0;
        if (end - begin < 100) {
            //如果拆分后的middle不足100，直接累加返回结果即可，不需要再拆分了
            for (int i = begin; i <= end; i++) {
                sum += i;
            }
        } else {
            //拆分
            int middle = (begin + end)/2;
            SumTask sumTask1 = new SumTask(begin, middle);
            SumTask sumTask2 = new SumTask(middle+1, end);
            sumTask1.fork();
            sumTask2.fork();

            //合并
            long sum1 = sumTask1.join();
            long sum2 = sumTask2.join();
            sum = sum1 + sum2;
        }
        return sum;
    }
}
