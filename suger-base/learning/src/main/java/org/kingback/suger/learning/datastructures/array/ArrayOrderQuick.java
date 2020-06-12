package org.kingback.suger.learning.datastructures.array;


/**
 * 数组的快速排序实现
 * 利用分治法
 * 基于递归的归并方案
 */
public class ArrayOrderQuick {

    /**
     * 分治法的主体思想
     * 1. 把数据按照折半的方式递归拆分成两个数组，直到数组的大小为1，此步骤为分
     * 2. 执行数组的合并，两个子数组合并入一个数组中，分别取两个数组的游标对比，依次向上完成分治
     *
     * @param srcArr 开始序号
     */
    public static void orderByMergeSort(int[] srcArr) {

        //1.获取当前数组的长度，对数组按照折半的方式分解成两个数组
        int length = srcArr.length;
        int mid = length / 2;

        //递归终止条件，数组长度为1，不能继续拆分
        if (length <= 1) {
            return;
        }


        //首先定义递归操作的二分操作
        int[] preArray = new int[mid];
        int[] plsArray = new int[length - mid];

        for (int i = 0; i < length; i++) {
            if (i < mid) {
                preArray[i] = srcArr[i];
            } else {
                plsArray[i - mid] = srcArr[i];
            }
        }

        //2.当前数组，折半后的两个数组通过递归的方式向下展开,使子数组有序
        if (length != mid) {
            orderByMergeSort(preArray);
            orderByMergeSort(plsArray);
        }


        //3.假定返回的两个子数组是已经排序的数组，对数组合并返回
        int[] temp = new int[length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (k < length) {
            //此处设置一个状态开关，判断i，j是否达到阈值
            if (i < mid && j < length - mid) {
                if (preArray[i] < plsArray[j]) {
                    temp[k++] = preArray[i];
                    i++;
                } else {
                    temp[k++] = plsArray[j];
                    j++;
                }
            } else {
                //剩余元素的补充,如果i仍然小于mid，表示pre有剩余
                if (i < mid) {
                    temp[k++] = preArray[i];
                    i++;
                } else {
                    temp[k++] = plsArray[j];
                    j++;
                }
            }
        }

        //temp复制回srcArray
        k = 0;
        while (k < length) {
            srcArr[k] = temp[k];
            k++;
        }
    }

}
