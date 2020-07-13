package org.kingback.suger.learning.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

/**
 * 树的搜索方式
 * 递归查找
 * 深度优先遍历DFS
 * 广度优先遍历BFS
 */
public class SearchType<K> {


    //定义一个树节点
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }


    /**
     * https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/
     * 对N叉树手动使用Stack遍历
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        /**
         * 思路：手动模拟压栈操作，使用stack，从root开始先压入栈
         * root 出栈，同时把root的子节点倒序入栈（保证出栈顺序为从左到右），出栈值写入List
         * 整个迭代过程属于BFS
         */
        if (root == null) return null;
        List<Integer> list=new ArrayList<>();
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        while (!stack.empty()) {
            Node cur = stack.pop();
            list.add(cur.val);
            if (cur.children != null) {
                for (int i=cur.children.size()-1;i>=0;i--) {
                    stack.push(cur.children.get(i));
                }
            }
        }
        return list;
    }

    //通过数据生成普通二叉树
    void gentreeFromArray(K[] arr) {

    }


    public void doBFS(K[] arr) {

    }
}
