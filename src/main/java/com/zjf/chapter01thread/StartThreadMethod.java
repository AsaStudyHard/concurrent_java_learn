/**
 * description:  创建线程的方式
 * date:         2022/8/28 12:31
 * author        ZhuJunfei
 */

package com.zjf.chapter01thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.StartThreadMethod")
public class StartThreadMethod {
    public static void testThread() {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.debug("extends thread running");
            }
        };
        t1.start();
    }

    public static void testRunnable() {
        Thread t2 = new Thread(() -> log.debug("implement runnable running"), "t2");
        t2.start();
    }

    public static void main(String[] args) {
        testRunnable();
        testThread();
    }
}
