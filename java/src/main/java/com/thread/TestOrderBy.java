package com.thread;

public class TestOrderBy {
    private int a;
    private boolean flag;

    public static void main(String[] args) {
        while(true){
            TestOrderBy testOrderBy = new TestOrderBy();
            Thread t1 = new Thread(()->{
                testOrderBy.reader();
            });
            Thread t2 = new Thread(()->{
                testOrderBy.write();
            });
            t1.start();
            t2.start();
        }

    }

    private void write(){
        a=1;
        flag=true;
    }

    private void reader(){
        if(flag){
            System.out.println(a);
        }
    }

}
