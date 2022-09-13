/**
 * description:  synchronized from class view
 * date:         2022/9/12 15:34
 * author        ZhuJunfei
 */

package com.zjf.chapter03synchronized;

public class Demo01SynchronizedClass {
    static final Object lock = new Object();
    static int count;
    public static void main(String[] args) throws InterruptedException {
        synchronized (lock){
            lock.wait();
            count ++;
        }
        System.out.println(count);
    }
}
