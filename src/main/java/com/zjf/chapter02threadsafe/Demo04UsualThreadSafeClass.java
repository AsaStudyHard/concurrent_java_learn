/**
 * description:  常见的线程安全类
 * date:         2022/9/3 21:25
 * author        ZhuJunfei
 */

package com.zjf.chapter02threadsafe;


import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Demo04UsualThreadSafeClass {
    public static Integer num = 1;

    public static void main(String[] args) throws InterruptedException {
        Hashtable<Integer, String> ht = new Hashtable<>();
        for (int i = 0; i < 2; i++) {
            Thread t1 = new Thread(() -> {
                if (ht.get(num) == null) {
                    ht.put(num, Thread.currentThread().getName());
                    num++;
                }
            }, "t" + i);
            t1.start();
            t1.join();
        }

//        TimeUnit.SECONDS.sleep(3);
        for (Map.Entry<Integer, String> entry : ht.entrySet()) {
            System.out.println(entry);
        }
    }

}
