package org.kingback.suger.demo;

/**
 * @author wangyang09
 * Created on 2021-04-07
 */
public class CheckRegrex {
    public static void main(String[] args) {
        String regStr = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[\\S]{6,20}$";
        String s1 = "1234567";
        String s2 = "123qwe7";
        String s3 = "qazWSX123";
        String res = String.format("s1 matchres:" + s1.matches(regStr) + " s2 matchres:" + s2.matches(regStr) + " s3 matchres:" + s3.matches(regStr));
        System.out.println(res);
    }
}
