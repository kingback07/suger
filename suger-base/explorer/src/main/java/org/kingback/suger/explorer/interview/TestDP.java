package org.kingback.suger.explorer.interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 动态规划面试
 */
public class TestDP {

    public static Integer maxSubSum(Integer[] array) {
        int sum = 0;
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
            if (sum < 0) {
                max = array[i];
                sum = 0;
            }
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }

    public static List<Integer> maxSubSumArray(Integer[] array) {
        int sum = 0;
        int max = array[0];

        //设计一个Map结构作为标量，计数过程中添加中间标识
        Map<Integer, Boolean> tempMap = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            //此处判断是否包含0决定输出双解情况下长数据还是短数组
            if (sum >= 0) {
                sum += array[i];
            } else {
                sum = array[i];
                tempMap.clear();
            }
            if (array[i] < 0) {
                tempMap.put(i, false);
            } else {
                tempMap.put(i, true);
            }
            if (sum > max) {
                max = sum;
                //置换Map中标量为false的数值进入到数组中
                for (Integer index : tempMap.keySet()) {
                    tempMap.put(index, true);
                }
            }
        }

        List<Integer> resList = new ArrayList<>();
        tempMap.entrySet().forEach(v -> {
            if (v.getValue()) {
                resList.add(array[v.getKey()]);
            }
        });

        return resList;
    }

}
