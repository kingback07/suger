package org.kingback.suger.learning;

import org.junit.Test;
import org.kingback.suger.learning.datastructures.array.ArrayOrderQuick;
import org.kingback.suger.learning.datastructures.array.ArrayOrderSimple;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

@SpringBootTest
public class LearningApplicationTests {

    private void printArray(int[] array, int length) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int epo = array.length > length ? length - 1 : array.length - 1;
        for (int i = 0; i < epo; i++) {
            sb.append(array[i] + ",");
        }
        sb.append(array[epo] + "]");
        System.out.println(sb.toString());
    }

    //随机生成一个size大小的数组
    private int[] randomArray(int size) {
        int[] res = new int[size];
        Random r = new Random(1);
        for (int i = 0; i < size; i++) {
            res[i] = r.nextInt(10 * size);
        }
        return res;
    }

    @Test
    public void arrayOrderByInsertTest() {
        int[] array = new int[]{10, 5, 9, 6, 2, 3, 8, 7, 1, 4};
        ArrayOrderSimple.orderByInsert(array);
        printArray(array, 100);
    }


    @Test
    public void arrayOrderByBubbleTest() {
        int[] array = new int[]{10, 5, 9, 6, 2, 3, 8, 7, 1, 4};
        ArrayOrderSimple.orderByBubble(array);
        printArray(array, 100);
    }

    @Test
    public void arrayOrderBySelectTest() {
//        int[] array = new int[]{10, 5, 9, 6, 2, 3, 8, 7, 1, 4};
//        ArrayOrderSimple.orderBySelected(array);
//        printArray(array, 100);
        int[] array = randomArray(100000);
        long sta = System.currentTimeMillis();
        ArrayOrderSimple.orderBySelected(array);
        long end = System.currentTimeMillis();
        System.out.println("选择排序，耗时:" + (end - sta) + "ms");
        printArray(array, 100);
    }


    @Test
    public void arrayOrderByMerge() {
        int[] array = randomArray(100000);
        long sta = System.currentTimeMillis();
        ArrayOrderQuick.orderByMergeSort(array);
        long end = System.currentTimeMillis();
        System.out.println("归并排序，耗时:" + (end - sta) + "ms");
        printArray(array, 100);
    }

    @Test
    public void compare() {
        System.out.println("排序算法比较,10w量级别，100w普通排序耗时已经超过10s，没有意义");

        int[] array0 = randomArray(100000);
        long sta0 = System.currentTimeMillis();
        ArrayOrderSimple.orderByBubble(array0);
        long end0 = System.currentTimeMillis();
        printArray(array0, 100);
        System.out.println("冒泡排序，耗时:" + (end0 - sta0) + "ms");

        int[] array1 = randomArray(100000);
        long sta1 = System.currentTimeMillis();
        ArrayOrderSimple.orderByInsert(array1);
        long end1 = System.currentTimeMillis();
        printArray(array1, 100);
        System.out.println("插入排序，耗时:" + (end1 - sta1) + "ms");

        int[] array2 = randomArray(100000);
        long sta2 = System.currentTimeMillis();
        ArrayOrderSimple.orderBySelected(array2);
        long end2 = System.currentTimeMillis();
        printArray(array2, 100);
        System.out.println("选择排序，耗时:" + (end2 - sta2) + "ms");

        int[] array3 = randomArray(100000);
        long sta3 = System.currentTimeMillis();
        ArrayOrderQuick.orderByMergeSort(array3);
        long end3 = System.currentTimeMillis();
        printArray(array3, 100);
        System.out.println("归并排序，耗时:" + (end3 - sta3) + "ms");

        ArrayList<Integer> arrayList = new ArrayList<>();
        int[] array4 = randomArray(100000);
        for (int i = 0; i < 100000; i++) {
            arrayList.add(array4[i]);
        }
        long sta4 = System.currentTimeMillis();
        Collections.sort(arrayList);
        long end4 = System.currentTimeMillis();
        System.out.println("Collection自带排序，耗时:" + (end4 - sta4) + "ms");

    }

}
