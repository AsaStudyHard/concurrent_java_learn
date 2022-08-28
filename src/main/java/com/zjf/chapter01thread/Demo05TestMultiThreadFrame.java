/**
 * description:  测试多线程栈帧
 * date:         2022/8/28 16:43
 * author        ZhuJunfei
 */

package com.zjf.chapter01thread;

public class Demo05TestMultiThreadFrame {
    public static void method1(int x) {
        int y = x + x;
        System.out.println(y);
        method2(21, 21);
    }
    public static void method2(int a, int b) {
        int sum = a + b;
        System.out.println("sum = " + sum);
    }
    public static void th(){
        Thread t1 = new Thread(() -> {
            System.out.println("线程执行了");
            method1(11);
            method2(11, 22);
        }, "t1");
        t1.start();
    }

    public static void main(String[] args) {
        th();
        method1(666);
        method2(222, 333);
    }
}
