package org.kingback.suger.learning.datastructures.node;

import java.util.HashMap;

/**
 * 创建一个具有LRU能力的Cache
 * 1. 首先是一个cache，对应一个Map，能够快速定位一个key对应的val。
 * 2. 需要设定一个 capacity,超过 capacity时，set操作执行LRU
 * 3. 存储的数据本身是一个Node，构建一个基于Node的FIFO队列，LRU时淘汰队尾元素
 */
public class LruCache<K, V> {

    private int CAPACITY = 5;
    private HashMap<K, DataNode> innerMap = new HashMap<>();
    private int count;

    public LruCache(Integer customerCap) {
        this.CAPACITY = customerCap > 0 ? customerCap : 5;
        head = new DataNode(0, 0);
        tail = head;
    }

    //从缓存获取
    public V get(K k) {
        if (innerMap.containsKey(k)) {
            DataNode existNode = innerMap.get(k);
            V val = (V) existNode.value;
            //从链表中移除原来的node
            delNode(existNode);
            //在链表中头插此node
            addHead(existNode);
            return val;
        }
        return null;
    }

    //设置缓存
    public void set(K k, V v) {
        if (innerMap.containsKey(k)) {
            delNode(innerMap.get(k));
            count--;
        }
        if (++count > CAPACITY) {
            doLRUClear();
            count--;
        }
        DataNode node = new DataNode(k, v);
        addHead(node);
        innerMap.put(k, node);
    }

    public void printNodes() {
        String contents = "{";
        for (K k : innerMap.keySet()) {
            contents += innerMap.get(k).toString() + ",";
        }
        contents = contents.substring(0, contents.length() - 1);
        contents += "}";
        System.out.println(contents);
    }

    private DataNode head;
    private DataNode tail;

    //构建一个双向链表来存储具体数据
    class DataNode<K, V> {
        K key;
        V value;

        DataNode prev;
        DataNode next;

        DataNode(K key, V val) {
            this.key = key;
            this.value = val;
        }

        @Override
        public String toString() {
            return "{Key:" + key + ",Val:" + value + "}";
        }
    }

    //节点头插
    private void addHead(DataNode node) {
        if (head.next != null) {
            head.next.prev = node;
            node.next = head.next;
            node.prev = head;
            head.next = node;
        } else {
            tail = node;
            head.next = node;
            node.prev = head;
        }
    }

    //删除末位节点
    private void doLRUClear() {
        DataNode node = tail;
        innerMap.remove(tail.key);
        tail = node.prev;
        tail.next = null;
    }

    //删除指定Node
    private void delNode(DataNode node) {
        node.prev.next = node.next;
        if (node != tail) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
    }

    public static void main(String[] args) {
        LruCache<String, String> cache = new LruCache<>(3);
        cache.set("1", "first");
        cache.printNodes();
        cache.set("2", "second");
        cache.printNodes();
        cache.set("2", "two");
        cache.printNodes();
        cache.set("3", "third");
        cache.printNodes();
        cache.set("4", "fourth");
        cache.printNodes();
        cache.set("4", "four");
        cache.printNodes();
        cache.set("5", "fifth");
        cache.printNodes();
    }


}
