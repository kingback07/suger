package org.kingback.suger.learning.datastructures.array;

/**
 * 简单数组排序方法实现
 * 冒泡，选择，插入
 * 统一为升序要求
 */
public class ArrayOrderSimple {


    /**
     * 冒泡排序法
     *
     * @param srcArr
     * @原理 迭代遍历数组，对当前元素和后一位元素对比，符合条件执行数据的交换，每次以冒泡的方式把最大的元素交换至最后一位并锁定位置
     * 单次迭代过程不再发生交换则表示此时待排序区已经稳定排序，整体数组已经有序生成，可终止迭代
     * 最坏情况下对倒序数组排序，复杂度O（n*n）,最优情况下数组已经完成排序只需要第一次迭代，复杂度O（n），N趋向无穷大时，复杂度平均为O（n*n）
     */
    public static void orderByBubble(int[] srcArr) {
        int length = srcArr.length;
        for (int i = 0; i < length; i++) {
            boolean hasChange = false;
            for (int j = 0; j < length - i - 1; j++) {
                int sta = srcArr[j + 1];
                int cur = srcArr[j];
                if (cur > sta) {
                    //触发交换条件，数据交换
                    srcArr[j + 1] = cur;
                    srcArr[j] = sta;
                    hasChange = true;
                }
            }
            if (!hasChange) {
                break;//如果没有交换发生，说明当前数据的顺序已经确定
            }
        }
    }

    /**
     * 插入排序法
     *
     * @param srcArr
     * @原理 把数组看成两个部分，已排序部分和未排序部分 依次从未排序部分取值在已排序部门通过对比插入，未排序部分为空时完成整体排序过程
     */
    public static void orderByInsert(int[] srcArr) {
        int length = srcArr.length;
        for (int i = 0; i < length; i++) {
            //i-1标示当前顺序区和非顺序区分界线,
            int curVal = srcArr[i];
            for (int j = i - 1; j >= 0; j--) {
                //对于非顺序区的第一位元素，需要与顺序区的最后一位数向前依次比较，直到此元素比顺序区中某一个元素大为止
                //j 标识为最终比较结果里匹配到的顺序区中应该插入的下标
                if (srcArr[j] > curVal) {
                    srcArr[j + 1] = srcArr[j];//j中所有剩下元素后移一步
                    srcArr[j]=curVal;
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 选择排序法
     *
     * @param srcArr
     * @原理 依然把数组看成两个部分即有序区和无序区，依次从无序区中提取出最小的数值后与无序区首位交换扩大有序区
     */
    public static void orderBySelected(int[] srcArr) {
        int length = srcArr.length;
        for (int i = 0; i < length; i++) {
            int minVal = srcArr[i];
            int temp = i;//第一次忽略中间变量的取值，默认为0，在出现0交换的场景下当前值会替换数组首位值
            for (int j = i; j < length; j++) {
                if (srcArr[j] < minVal) {
                    minVal = srcArr[j];
                    temp = j;
                }
            }
            //swap minval to array[i]
            if (temp != i) {
                int tempVal = srcArr[i];
                srcArr[temp] = tempVal;
                srcArr[i] = minVal;
            }


        }
    }


}
