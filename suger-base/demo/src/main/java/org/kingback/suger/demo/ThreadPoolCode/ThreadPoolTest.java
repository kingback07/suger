package org.kingback.suger.demo.ThreadPoolCode;


import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {

    static class CusThread implements Runnable {

        private int tid;
        private long mockIO;

        CusThread(int tid, long mockTime) {
            this.tid = tid;
            this.mockIO = mockTime;
        }

        @Override
        public void run() {
            System.out.println("start do CustomThread --> " + tid + "，and the thread will sleep " + mockIO + "ms");
            try {
                Thread.sleep(mockIO);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("finish do CustomThread --> " + tid);
        }
    }


    public static void main(String[] args) {

        ExecutorService ec = Executors.newCachedThreadPool();
        Random rd = new Random();
        for (int i = 0; i <= 10; i++) {
            long sleepTime = rd.nextInt(10) * 1000L;//随机10s内休眠时间
            CusThread cusThread = new CusThread(i, sleepTime);
            ec.submit(cusThread);
        }
        System.out.println(Integer.SIZE);
    }

}
