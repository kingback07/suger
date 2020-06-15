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

    /**
     * 快排方法的主体思想
     * 随机选取一个数作为标量，遍历数组，以此标量为标准，小于此标量的数据存放在标量数的左边，大于此标量的数据存放在标量数的右边，标量值放置在两个区间之间。
     * 分别对左边区间和右边区间的数据再次执行排序开始递归操作
     * 当子区间无法执行操作时递归终止
     *
     * @param srcArr
     */
    public static void orderByQuickSort(int[] srcArr) {
        quicksort(srcArr, 0, srcArr.length - 1);
    }

    /**
     * 快排实现子方法体（递归部分）
     *
     * @param arr   待快排array
     * @param start 开始索引
     * @param end   结束索引
     */
    private static void quicksort(int[] arr, int start, int end) {
        /**
         * 实现思路，从end位置提取标量值 z，从start位置提取游标i开始遍历 x，设置临时变量记录发生交换后标量对应下标
         * 如果 x<y 不做任何操作
         * 如果 x>y 互换xy的位置，
         * *试想如果从end的前一位再取一个启动下标j，向前遍历，每次取值是z
         * *对比 z，y 操作一致（从区间两边向中间遍历加快区间较大时的遍历速度）
         * *当i>=j时表示数组遍历完成，相当于在一次循环中处理前后两个元素
         * ****
         * 后记：想法很美好，实现方知少，如果y>x>z,对于[x,y,z],单次循环时，第一次交换发生后得到[z,y,x],但是中间变量位置已经被修改成0，此时y>z 成立，依然会触发交换，但是其实已经不需要交换了。
         * 那么继续考虑，y判断时需要添加前置条件(是否已经触发一次交换，如果没有，此次交换是安全的，如果已经发生交换,判断y位置是否已经在中间变量的右边，如果不在则继续交换)
         * 同理，位置的改变会影响下一次循环X的判断，所以也需要加上前置判断
         * 如果没有前后双向逼近，可能不会存在这个问题，因为单次循环内只会发生一次确定的变化
         * 综上：还是使用单向逼近更平稳简单
         * ****
         * 以最终中间量为区间分界点，左右子区间继续递归操作
         */

        int i, j;
        int pivot = arr[end];
        i = start;
        j = end - 1;

        if (start >= end) {
            return;
        }

        /**
         * 快速排序的主要步骤：如何在O（n/2）复杂度内确定标量值的分界点位置
         * 实现逻辑：使用哨兵职能，取i，j分别从两边向中间遍历，先从左边出发，如果查到左边有高于标量值的元素时停下，等待右边查询到低于标量值元素时，执行互换
         * 细节：数组是从左向右递增，对比最后一位，在最后一位已经是Max的情况下，不应该发生交换，但是确保标示位尽量靠后以达到无序数组能继续递归排序的能力。而最后一位已经是Min的情况下，执行交换后，标示位尽量靠前
         */
        while (i < j) {
            /**
             * 死循环风险，对于数组【9，1，3】,会出现 i，j不会执行递增操作导致i<j 恒成立！！！
             */
            while (arr[i] < pivot && i < j) {
                i++;
            }
            while (arr[j] > pivot && i < j) {
                j--;
            }
            //上述循环停止表示 出现符合条件的元素
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                //避免死循环，发生交换后，i,j需要继续移动
                i++;
                j--;
            }
        }
        int tempIdx = j;//定义预设的变量为当前右边指针位置
        //此处arr[j]作为和end值的待选交换值，需考虑如果arr[j]<pivot,说明end值已经是最大值，不应该发生交换
        if (arr[j] > pivot) {
            arr[end] = arr[j];
            arr[j] = pivot;
            tempIdx = j - 1;
        }

        //循环结束后，进入递归操作

        if (j > 1) {
            quicksort(arr, start, tempIdx);
        }
        quicksort(arr, j + 1, end);

    }

}
