/**
 * description:  interrupt sleep learn
 * date:         2022/8/28 23:45
 * author        ZhuJunfei
 */

package com.zjf.chapter01thread;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

@Slf4j(topic = "c.Demo08InterruptSleep")
public class Demo08InterruptSleep {
    public static void main(String[] args) throws InterruptedException {
        test1();
    }
    private static void test1() throws InterruptedException {
        Thread t1 = new Thread(()->{
            try {
                log.debug("打断之前, 以及睡眠之前的的状态: {}", Thread.currentThread().isInterrupted());
                sleep(2);
            } catch (InterruptedException e) {
                log.debug("{} 被打断了, 此时的状态是{}", Thread.currentThread().getName(), Thread.currentThread().isInterrupted());
                e.printStackTrace();
            }
        }, "t1");

        t1.start();


        sleep(1);
        t1.interrupt();
        log.debug(" 打断后的状态: {}", t1.isInterrupted());
    }
}
