package org.kingback.suger.demo.ThreadPoolCode;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangyang09 wangyang09@kuaishou.com>
 * Created on 2021-03-30
 */
public class MultiThreadTest {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        /**
         * 依次打印，关键
         * 定义一个状态锁，获取到锁的线程，执行规定次数的计算后，释放锁
         */
        AtomicInteger pubWriteSource = new AtomicInteger();
        Thread threadA = new Thread(() -> {
            while (true) {
                if (pubWriteSource.get() > 1000) {
                    break;
                }
                try {
                    lock.lock();
                    for (int i = 0; i < 10; i++) {
                        pubWriteSource.getAndIncrement();
                        System.out.println("ThreadA-->" + pubWriteSource);
                    }
                    condition2.signal();
                    condition1.await();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread threadB = new Thread(() -> {
            while (true) {
                if (pubWriteSource.get() > 1000) {
                    break;
                }
                try {
                    lock.lock();
                    for (int i = 0; i < 10; i++) {
                        pubWriteSource.getAndIncrement();
                        System.out.println("ThreadB-->" + pubWriteSource);
                    }
                    condition1.signal();
                    condition2.await();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        threadA.start();
        threadB.start();

    }
}
