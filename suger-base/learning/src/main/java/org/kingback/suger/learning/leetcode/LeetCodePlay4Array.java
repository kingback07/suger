package org.kingback.suger.learning.leetcode;

/**
 * LeetCode 玩法
 * Solution-编号
 * 题目描述
 * 代码思想阐述
 */
public class LeetCodePlay4Array {

    /**
     * https://leetcode-cn.com/problems/move-zeroes/
     *
     * @return
     */
    public static void Solution283(int[] nums) {
        if (nums.length <= 1) return;
        int i = 0;
        int j = 0;
        /**
         * 思路：双指针，i执行探测，如果非0,则与j指针互换位置，j移动
         * 保证i遍历结束后，j之前的所有元素都是非0
         * 再执行一次循环，把j之后的所有元素赋值为0
         */
        for (; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        for (; j < nums.length; j++) {
            nums[j] = 0;
        }
    }

    /**
     * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        /**
         * 思路：利用双指针和数据排序特性，如果num[i]!=num[j]
         * 则执行替换操作同时j向后移动，i遍历完成后，j的索引即指向数据长度
         */


        if (nums.length <= 0) return 0;
        int i = 0;
        int j = 0;
        for (; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[++j] = nums[i];
            }
        }
        return j;
    }

    /**
     * leetcode-11,盛水最多的容器
     * https://leetcode-cn.com/problems/container-with-most-water/
     */
    public int maxArea(int[] height) {

        /**
         * 思路：最大面积求解，分析影响因素：子数组长度，子数组左右元素的极小值
         * 使用left和right标识左右节点，取二者最小值minPort和二者差值lenght，有curArea=min（left,right）*(right-left)
         * left和right向中间逼近，取max（curArea）
         */
        if (height.length <= 1) return 0;
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            int length = right - left;
            int realHeight = height[left] < height[right] ? height[left] : height[right];
            int curArea = length * realHeight;
            maxArea = curArea > maxArea ? curArea : maxArea;
            if (realHeight == height[left]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }


    public static void main(String[] args) {

    }

}
