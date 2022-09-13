/**
 * description:  wait 和 notifyall的规范使用
 * date:         2022/9/13 16:03
 * author        ZhuJunfei
 */

package com.zjf.chapter04threadstop;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j(topic = "c.Demo04WaitNotifyAllStand")
public class Demo04WaitNotifyAllStand {
    static final Object obj = new Object();
    static Boolean isCigarette = false;
    static Boolean isTakeaway = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (obj) {
                while (!isCigarette) {
                    // 代码执行至此, 表示没有烟, 继续wait
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        log.debug("cigarette thread is interrupt");
                    }
                }

                // 代码执行至此. 表示有烟了
                log.debug("开始干活了");
            }
        }, "cigarette").start();

        new Thread(() -> {
            synchronized (obj) {
                while (!isTakeaway) {
                    // 代码执行至此, 表示没有烟, 继续wait
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        log.debug("cigarette thread is interrupt");
                    }
                }

                // 代码执行至此. 表示有烟了
                log.debug("开始干活了");
            }
        }, "takeAway").start();

        log.debug("主线程开始睡会");
        TimeUnit.SECONDS.sleep(1);
        // 让吸烟的干活了
        isCigarette = true;
        // 唤醒全部的线程
        synchronized (obj){
            obj.notifyAll();
        }
    }
}
