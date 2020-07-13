package org.kingback.suger.explorer.myquene;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 模拟一个队列，对于队列的操作只有 set 和 get
 * 要求：
 * 队列需要有一个容限capacity,等于此容限值的时候，进行set阻塞操作
 * 当队列项为空的时候，进行get的阻塞操作
 * <p>
 * 思路：
 * 1. 需要有个容器来承载对象，可使用Node或者Array实现，也可以使用java封装的容器对象实现
 * 2. 阻塞的实现，可以通过简单cas操作，也可以直接使用lock()或者 wait()+notify()的形式操作。避免粗暴使用synchronizd直接进行同步，会导致整个对象产生阻塞，效率低下
 */
public class QueneUnit<E> {

    Node<E> header;
    Node<E> last;

    int capacity;
    volatile int size;

    final ReentrantLock putlock = new ReentrantLock();
    final ReentrantLock getlock = new ReentrantLock();

    final Condition putCondition = putlock.newCondition();
    final Condition getCondition = getlock.newCondition();

    static class Node<E> {

        E value;
        Node<E> next;

        Node(E value) {
            this.value = value;
        }

        Node() {
        }

    }


    public QueneUnit(int cap) {
        capacity = cap > 0 ? cap : 0;
        last = new Node<>();
        header = last;
        size = 0;
    }

    //包装数据入队列
    private void enqueue(E e) {
        last = last.next = new Node<>(e);
    }

    //包装数据出队列
    private E dequeue() {
        Node<E> h = header;
        Node<E> first = header.next;

        h = h.next;
        E val = first.value;
        first.value = null;
        header = first;

        return val;
    }

    /**
     * 插入分析：
     * 1.使用volatile size 来判断当前值是否等于容量，如果等于容量，执行putlockcondition.await(),此时线程阻塞，释放对应锁资源，但不影响消费操作
     * 2.如果产生了消费，那么消费结束后，size减少
     * 3.新的线程执行插入时，获取到putlock，执行插入操作，并且判断当前值+1是否<容量，如果小于，则唤醒等待的插入线程
     *
     * @param obj
     * @throws InterruptedException
     */
    public void put(E obj) throws InterruptedException {
        putlock.lock();//加写锁，保证插入操作同步
        try {
            while (size == capacity) {
                //进入阻塞，等待消费线程消费后通知
                putCondition.await();
            }
            Thread.sleep(500);
            enqueue(obj);
            size++;
//            System.out.println(Thread.currentThread().getId() + ":插入操作，当前size:" + size);
            if (size < capacity)
                putCondition.signalAll();//唤醒读锁

            if(size>=capacity){
                getlock.lock();
                getCondition.signalAll();
                getlock.unlock();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            putlock.unlock();
        }
    }


    public E get() throws InterruptedException {

        getlock.lock();//加读锁，保证取值操作同步
        try {
            while (size == 0) {
                //进入阻塞，等待消费线程消费后通知
                getCondition.await();
            }
            E result = dequeue();
            size--;
//            System.out.println(Thread.currentThread().getId() + ":消费操作，当前size:" + size);
            if (size > 0)
                getCondition.signalAll();
            if (size <= 0) {
                putlock.lock();
                putCondition.signalAll();
                putlock.unlock();
            }
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            getlock.unlock();
        }
    }


    public static void main(String[] args) {
        QueneUnit que = new QueneUnit<Integer>(10);
        try {
            for (int i = 1; i <= 100; i++) {
                int val = i;
                new Thread(() -> {
                    try {
                        que.put(val);
                        System.out.println("put obj to queue:" + val);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
                new Thread(() -> {
                    try {
                        Integer x = (Integer) que.get();
                        System.out.println("get obj from queue:" + x);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
