package org.kingback.suger.explorer;

/**
 * @Author: wangyang09
 * @Date 2021/7/5
 **/
public class JNITest {
    public native static int createIMeasure(int x, int y);

    static {
        System.load("/Users/wangyang09/Downloads/libIMeasure.so");
    }


    public static void main(String[] args) {
        System.out.println(createIMeasure(1, 1));
    }

}
