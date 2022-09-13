/**
 * description:  wait的快速入门
 * date:         2022/9/13 14:56
 * author        ZhuJunfei
 */

package com.zjf.chapter04threadstop;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j(topic = "c.Demo01WaitQuick")
public class Demo01WaitQuick {
    static final Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (obj) {
                log.debug("哎, 我执行了...");
                log.debug("entry waiting");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("哇, 我执行不到");
            }
        }, "t1").start();
        TimeUnit.SECONDS.sleep(2);
        log.debug("2s后, 主线程执行完了");
    }
}
