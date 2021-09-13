package org.kingback.suger.demo.ThreadPoolCode;

import java.util.concurrent.locks.LockSupport;

public class TestPark {

    private static boolean parkTest(Thread thread) {
        System.out.println("Into Park Test");
        LockSupport.park(thread);
        return Thread.interrupted();
    }

    public static void main(String[] args) throws InterruptedException {

        Thread parkThread = new Thread(() -> {
            boolean isInterrupted = parkTest(Thread.currentThread());
            if (isInterrupted) {
                System.out.println("Thread has been interrupted");
            }
            System.out.println("Thread has been call up");
        });
        parkThread.start();
        Thread.sleep(5000);
        Thread callUpThread = new Thread(() -> {
            LockSupport.unpark(parkThread);
        });
        callUpThread.start();
    }

}
