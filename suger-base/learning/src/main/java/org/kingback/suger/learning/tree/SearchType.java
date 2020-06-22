package org.kingback.suger.learning.tree;

/**
 * 树的搜索方式
 * 递归查找
 * 深度优先遍历DFS
 * 广度优先遍历BFS
 */
public class SearchType<K> {

    TreeNode root;

    //定义一个树节点
    class TreeNode {
        K value;
        TreeNode left;
        TreeNode right;

        void appendLeft(TreeNode leftNode) {
            left = leftNode;
        }

        void appendRigth(TreeNode rightNode) {
            right = rightNode;
        }

        TreeNode(K val) {
            value = val;
        }
    }

    //通过数据生成普通二叉树
    void gentreeFromArray(K[] arr) {
        root = new TreeNode(arr[0]);
        for(int i=1;i<arr.length;i++){
            K val=arr[i];
            TreeNode node=new TreeNode(val);
        }
    }

    private void addNode(TreeNode cur,TreeNode pre){
        if(cur.left==null&&cur.right==null){
           
        }
    }

    public void doBFS(K[] arr) {

    }
}
