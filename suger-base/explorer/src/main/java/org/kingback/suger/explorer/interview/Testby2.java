package org.kingback.suger.explorer.interview;

/**
 * 关于2的面试
 */
public class Testby2 {

    /**
     * 写一个方法，快速判断这个数是否是2的N次幂
     */
    public static boolean isTrue(Integer par) {
        boolean res = false;

        //参数-1向右移动1位数
        Integer parPlus = (par - 1) >>> 1;
        //与原参数&比较获取结果，为0则为true，否则为false

        if ((par & parPlus) == 0) {
            res = true;
        }
        return res;
    }

    /**
     * 写一个方法，快速判断这个数是2的多少次方
     */
    public static int quaNum4two(Integer par) throws Exception {
        //如果此数不是2的整数幂，则抛出异常
        int t = (par - 1) >>> 1;
        t = par & t;
        if (t != 0) {
            throw new Exception("这不是一个2的整次幂");
        }
        int num = 0;
        int count = par - 1;
        while (count != 0) {
            count = count & (count - 1);
            num++;
        }
        return num;
    }
}
