/**
 * description:  打断 park 线程
 * date:         2022/8/29 17:59
 * author        ZhuJunfei
 */

package com.zjf.chapter01thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

@Slf4j(topic = "c.Demo11InterruptParkThread")
public class Demo11InterruptParkThread {
    public static void main(String[] args) throws InterruptedException {
        Thread park_thread = new Thread(() -> {
            log.debug("线程进入park状态  ----  ");
            LockSupport.park();
            log.debug("park状态线程被打断, 此时的线程状态是: {}", Thread.currentThread().isInterrupted());
        }, "park thread");
        park_thread.start();
        TimeUnit.SECONDS.sleep(1);
        log.debug("主线程打断park线程   ---  ");
        park_thread.interrupt();
    }
}
