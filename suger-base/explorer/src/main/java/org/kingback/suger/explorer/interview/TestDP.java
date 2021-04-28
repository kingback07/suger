package org.kingback.suger.explorer.interview;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.concurrent.*;

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

    /**
     * 使用动态规划获取子数组最大和
     *
     * @param array
     * @return
     */
    public static int maxSubSumByDB(int[] array) {
        /**
         * 思路：建立dp，假设第i-1个值的有最大值dp[i]
         * 对于i，需要判断array[i]是否为正数，如果为正数则dp[i]=dp[i-1]+array[i]
         * 如果array[i]为负数，dp[i]=dp[i-1]
         *
         * 状态转移方程
         * [6,-1,-2,7]->[6,6,6,10]
         * [-1,-2,-3,6]->[-1,-1,-1,6]
         * [6,-3,-5,9]->[6,6,6,9];
         * [7,-2,1,3]->[7,7,7,9]
         * 分析：dp[3]<>a[3]<>dp[2]+a[3],无法直接产生dp[i] 和 dp[i-1] 之间的联系
         * 状态与当前数组累加值 sum 相关
         * 对于a[i]，sum[i-1] 两个影响因素
         * 状态转移方程需要综合考虑 sum[] 和 a[] 两个影响变量
         * if(sum<0){
         *     sum=a[i]
         * }else{
         *    sum+=a[i]
         * }
         * max=math.Max(max,sum);
         *
         * max=dp[i];
         * 即
         * if(sum[i-1]<0){
         *     dp[i]=a[i];
         * }else{
         *     dp[i-1]=math.Max(sum[i-1]+a[i],dp[i]) //状态转移方程
         * }
         *
         * so：do the loop
         * int sum,int max
         * sum=max=a[0]
         * for(i=1,i<lenght,i++){
         *
         * }
         */
        int max, sum;
        max = sum = array[0];
        if (array.length > 1) {
            for (int i = 1; i < array.length; i++) {
                sum = sum > 0 ? sum += array[i] : array[i];
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    public static int lengthOfLongestSubstring(String s) {
        /**
         * DP思想：假设当前dp[i-1]是前s(0~i)中非重复子串最长
         * 根据s(i)判断是否存在重复字符，如果存在，dp[i]为dp[i-1]基础之上不变
         * 如果不存在，比较dp[i-1]和max+=1的最大值
         */

        if (s == null || s.length() == 0) {
            return 0;
        }
        int length = s.length();
        HashMap<Character, Integer> dict = new HashMap<Character, Integer>();
        int[] dp = new int[length];
        dp[0] = 1;
        int max = 1;
        dict.put(s.charAt(0), 0);
        for (int i = 1; i < length; i++) {
            char cur = s.charAt(i);
            if (!dict.containsKey(cur)) {
                max += 1;
            } else {
                //对于尾数出现重复进行额外判断(当尾数重复且不是与前一个字符重复时，max依然+1)
                if (i == length - 1 && dict.get(cur) < (i - 1)) {
                    max += 1;
                } else {
                    max = 0;
                }
            }
            dict.put(cur, i);
            dp[i] = Math.max(dp[i - 1], max);
        }
        return dp[length - 1];
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

//        java.util.concurrent.ThreadPoolExecutor

        return resList;
    }

    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];

        if (coins.length <= 0) {
            return -1;
        }

        int clength = coins.length;
        for (int i = 0; i <= amount; i++) {
            dp[i] = amount + 1;
            for (int j = 0; j < clength; j++) {
                int val = coins[j];
                if (i - val > 0) {
                    dp[i] = Math.min(dp[i], dp[i - val] + 1);
                }
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    public static void main(String[] args) {
//        int[] array = new int[]{1, -2, 3, 10, -4, 7, 2, -5};
        int[] array = new int[]{5,-1,-2,-3,2};
        int max = maxSubSumByDB(array);
        System.out.println("Max Value is:" + max);
//        String s = "aab";
//        int maxSubStrlen = lengthOfLongestSubstring(s);
//        System.out.println("最长子序列：" + maxSubStrlen);


//        int[] coins = new int[] {1, 2, 5};
//        int minVal = coinChange(coins, 11);
//        System.out.println("最少硬币数量：" + minVal);


    }

}
