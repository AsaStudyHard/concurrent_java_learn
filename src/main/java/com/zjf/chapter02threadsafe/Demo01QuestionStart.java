/**
 * description:  问题引入
 * date:         2022/8/29 21:11
 * author        ZhuJunfei
 */

package com.zjf.chapter02threadsafe;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Demo01QuestionStart")
public class Demo01QuestionStart {
    static int counter = 0;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (lock){
                    counter++;
                }
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (lock){
                    counter--;
                }
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("{}",counter);
    }

}

