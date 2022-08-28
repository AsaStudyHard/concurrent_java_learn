/**
 * description:  start 前后的线程状态
 * date:         2022/8/28 17:19
 * author        ZhuJunfei
 */

package com.zjf.chapter01thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Demo06StartState")
public class Demo06StartState {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> log.debug("runnign ..."), "t1");
        log.debug("start before state:  {}", t1.getState());
        t1.start();
        log.debug("start after state:  {}", t1.getState());
    }
}
