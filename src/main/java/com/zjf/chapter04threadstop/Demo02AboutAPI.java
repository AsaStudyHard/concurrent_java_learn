/**
 * description:  关于wait的一些api
 * date:         2022/9/13 15:08
 * author        ZhuJunfei
 */

package com.zjf.chapter04threadstop;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j(topic = "c.Demo02AboutAPI")
public class Demo02AboutAPI {
    static final Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized(obj){
                log.debug("{} 正在执行, 进入了wait set了", Thread.currentThread().getName());
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    log.debug("{} 被打断了", Thread.currentThread().getName());
                    e.printStackTrace();
                }
                log.debug("从wait set中出来了");
            }

        }, "power").start();

        new Thread(() -> {
            synchronized(obj){
                log.debug("{} 正在执行, 进入了wait set了", Thread.currentThread().getName());
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    log.debug("{} 被打断了", Thread.currentThread().getName());
                    e.printStackTrace();
                }
                log.debug("{} 从wait set中出来了", Thread.currentThread().getName());
            }

        }, "police").start();

        TimeUnit.SECONDS.sleep(1);
        log.debug("main thread 睡醒了, 开始wake up 了");
        synchronized (obj){
//            obj.notify();
            obj.notifyAll();
        }
        log.debug("wake up finish");
    }
}
