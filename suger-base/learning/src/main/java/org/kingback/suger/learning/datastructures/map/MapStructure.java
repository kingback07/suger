package org.kingback.suger.learning.datastructures.map;

import java.util.HashMap;

/**
 * HashMap 数据结构模拟
 *
 * @Author: wangyang09
 * @Date 2021/7/6
 **/
public class MapStructure<K, V> {


    /**
     * 数据实际存储节点，单向链表
     *
     * @Author: wangyang09
     * @Date 2021/7/6
     **/
    class Node<K, V> {
        final int hash;
        final K key;
        V val;
        Node<K, V> next;

        public Node(int hash, K key, V val, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.val = val;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getVal() {
            return val;
        }

        public void setVal(V val) {
            this.val = val;
        }
    }

    class TreeNode<K, V> {
        final int hash;
        final K key;
        V val;

        TreeNode<K, V> parent;  // red-black tree links
        TreeNode<K, V> left;
        TreeNode<K, V> right;
        TreeNode<K, V> prev;    // needed to unlink next upon deletion
        boolean red; //红黑树标记

        public TreeNode(K key,int hash, TreeNode<K, V> parent, TreeNode<K, V> left, TreeNode<K, V> right,
                        TreeNode<K, V> prev, boolean red) {
            this.key=key;
            this.hash = hash;
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.prev = prev;
            this.red = red;
        }

        final TreeNode<K, V> find(int h, Object k, Class<?> kc) {
            TreeNode<K, V> p = this;
            do {
                int ph, dir;
                K pk;
                TreeNode<K, V> pl = p.left, pr = p.right, q;
                //hash<当前节点hash，节点转换为左子树
                if ((ph = p.hash) > h) {
                    p = pl;
                }
                //hash > 当前节点hash，节点转换为右子树
                else if (ph < h) {
                    p = pr;
                }
                //key 一致的情况
                else if ((pk = p.key) == k || (k != null && k.equals(pk))) {
                    return p;
                }
                // 左节点为空，切换为右节点
                else if (pl == null) {
                    p = pr;
                }
                // 右节点为空，切换为右节点
                else if (pr == null) {
                    p = pl;
                }
//                else if ((kc != null ||
//                        (kc = comparableClassFor(k)) != null) &&
//                        (dir = compareComparables(kc, k, pk)) != 0) {
//                    p = (dir < 0) ? pl : pr;
//                }
                else if ((q = pr.find(h, k, kc)) != null) {
                    return q;
                } else {
                    p = pl;
                }
            } while (p != null);
            return null;
        }
    }

    //Map 数据结构： 单向链表数组
    Node<K, V>[] table;

    /**
     * 模拟根据Key 查找值
     *
     * @Author: wangyang09
     * @Date 2021/7/6
     **/
    public V getVal(K key) {
        Node<K, V> e;
        return (e = getNode(hash(key), key)) == null ? null : e.getVal();
    }

    //Hash 优化，防止Hash后的数值与 tableSize 做&操作时，高位被抹平
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }


    final Node<K, V> getNode(int hash, Object key) {
        Node<K, V>[] tab;
        Node<K, V> first, e;
        int n;
        K k;


        /**
         * 1. table 初始化不为空
         * 2. first=tab[(n-1)&hash] 计算 keyHash 对应的数组中索引
         * 3. tab[first] 获取数组中链表的头节点
         */
        if ((tab = table) != null && (n = tab.length) > 0 &&
                (first = tab[(n - 1) & hash]) != null) {


            /**
             * 1. 头节点hash 验证
             * 2. 满足 key 一致 或者 key值相同
             */
            if (first.hash == hash && // always check first node
                    ((k = first.key) == key || (key != null && key.equals(k)))) {
                return first;
            }

            /**
             * 不满足以上条件表明：key因为hash冲突属于 链表 中的某个节点
             */
            if ((e = first.next) != null) {
//                //对于平衡树类型的节点类型，使用树查找
//                if (first instanceof HashMap.TreeNode) {
//                    return ((HashMap.TreeNode<K, V>) first).getTreeNode(hash, key);
//                }
                /**
                 * 链表遍历
                 * 终止条件 (e=e.next)!=null
                 */
                do {
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k)))) {
                        return e;
                    }
                } while ((e = e.next) != null);
            }
        }
        return null;
    }


}
