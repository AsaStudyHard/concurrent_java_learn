/**
 * description:  测试栈帧
 * date:         2022/8/28 16:28
 * author        ZhuJunfei
 */

package com.zjf.chapter01thread;

public class Demo04TestFrame {
    public static void method1(int x) {
        int y = x + x;
        System.out.println(y);
        method2(21, 21);
    }

    public static void method2(int a, int b) {
        int sum = a + b;
        System.out.println("sum = " + sum);
    }

    public static void main(String[] args) {
        System.out.println("main running");
        method1(22);
    }
}
