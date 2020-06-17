package org.kingback.suger.learning.datastructures.array;

import java.util.HashMap;

/**
 * 字符串匹配算法
 * 主串S1 ，模式串S2
 * 1. 基础的暴力匹配BF，O(m*n)
 * 2. 对模串Hash后主串依次滑动取Hash比较RK，相比BF只是不需要每个字符比对，降低了一些时间复杂度并提高稳定性，总体效率仍然是O（m*n）
 * 3. BM算法
 * 4. KMP
 */
public class StringCompare {

    public static void compareByBF(String s1, String s2) {
        char[] sc1 = s1.toCharArray();
        char[] sc2 = s2.toCharArray();
        int i = 0;
        boolean iscompare = false;
        while (i < sc1.length) {
            iscompare = true;
            for (int j = 0; j < sc2.length; j++) {
                if (sc1[i + j] != sc2[j]) {
                    i++;
                    iscompare = false;
                    break;
                }
            }
            if (iscompare) {
                System.out.println("查找到匹配字符串，开始位置：" + i);
                break;
            }
        }
        if (!iscompare) {
            System.out.println("字符串不匹配");
        }
    }


    /**
     * 输入: ["flower","flow","flight"] 输出: "fl"
     * 思路一：利用一条额外数组，遍历每个数组的字节，向数组中添加字节，如果字节不相等就退出
     * 遍历数组构建最终串
     * 思路二：
     *
     * @param strs
     * @return
     */
    public static String getLongestPrefixA(String[] strs) {
        int length = strs[0].length();
        int count = strs.length;
        for (int i = 0; i < length; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < count; j++) {
                if (i == strs[j].length() || c != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return "";
    }

    /**
     * 输入: ["flower","flow","flight"] 输出: "fl"
     * 思路二：利用递归分治的方式，对数组执行分割，如果子数组只有一个字符串时返回该字符串，如果子数组里有两个字符串时则返回两个字符串的前缀
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }

        int mid = strs.length / 2;
        String[] pre = new String[mid];
        String[] suf = new String[strs.length - mid];
        int k = 0;
        while (k < strs.length) {
            if (k < mid) {
                pre[k] = strs[k];
            } else {
                suf[k - mid] = strs[k];
            }
            k++;
        }

        String preRes = longestCommonPrefix(pre);
        String sufRes = longestCommonPrefix(suf);

        if (preRes.length() * sufRes.length() <= 0) {
            return "";
        }

        for (int i = 0; i < preRes.length(); i++) {
            char c = preRes.charAt(i);
            if (c != sufRes.charAt(i) || i == sufRes.length() - 1) {
                return preRes.substring(0, c != sufRes.charAt(i) ? i : i + 1);
            }
        }
        return preRes;
    }

}
