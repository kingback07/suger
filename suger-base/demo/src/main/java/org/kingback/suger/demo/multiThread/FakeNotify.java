package org.kingback.suger.demo.multiThread;

import java.util.concurrent.TimeUnit;

public class FakeNotify {

    private static class ResourceDemo {

        private int count = 0;

        public synchronized void input() {
            if (count >= 1) {
                System.out.println(Thread.currentThread().getName() + ":" +"to the limit of count max");
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count++;
            System.out.println(Thread.currentThread().getName() + ":" + count);
            this.notifyAll();

        }

        public synchronized void output() {
            if (count <= 0) {
                System.out.println(Thread.currentThread().getName() + ":" +"to the limit of count min");
                try {
                    /**
                     * wait() 方法释放锁，线程挂起，其他线程有机会进入
                     * 此处存在多个线程执行此处时被wait
                     * notifyAll 唤醒此处执行的多个线程，则 if 判断失效
                     */
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count--;
            System.out.println(Thread.currentThread().getName() + ":" + count);
            this.notifyAll();
        }

        public static void main(String[] args) {

            ResourceDemo rs = new ResourceDemo();

            new Thread(() -> {
                for (int i = 0; i <= 20; i++) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    rs.input();
                }
            }, "A").start();

            new Thread(() -> {
                for (int i = 0; i <= 20; i++) {
                    rs.output();
                }
            }, "B").start();

            new Thread(() -> {
                for (int i = 0; i <= 20; i++) {
                    rs.input();
                }
            }, "C").start();

            new Thread(() -> {
                for (int i = 0; i <= 20; i++) {
                    rs.output();
                }
            }, "D").start();


        }


    }


}
