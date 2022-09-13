/**
 * description:  sleep compare wait
 * date:         2022/9/13 15:33
 * author        ZhuJunfei
 */

package com.zjf.chapter04threadstop;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j(topic = "c.Demo03SleepWaitCompare")
public class Demo03SleepWaitCompare {
    static final Object obj = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (obj) {
                while (true) {

                }
            }
        }, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            log.debug("I am sleeping ");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("wake up me");
        }, "t1").start();
    }
}
