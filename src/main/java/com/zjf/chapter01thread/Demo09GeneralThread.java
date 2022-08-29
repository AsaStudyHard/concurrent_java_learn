/**
 * description:  打断普通的 线程
 * date:         2022/8/29 14:21
 * author        ZhuJunfei
 */

package com.zjf.chapter01thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Demo09GeneralThread")
public class Demo09GeneralThread {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) { }
        }, "乌拉~");
        // 启动线程
        t1.start();

        log.debug("interrupt before flag = {}", t1.isInterrupted());
        t1.interrupt();
        log.debug("interrupt after flag = {}", t1.isInterrupted());
    }
}
