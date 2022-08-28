/**
 * description:  sleep and yield learn
 * date:         2022/8/28 18:02
 * author        ZhuJunfei
 */

package com.zjf.chapter01thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j(topic = "c.Demo07SleepAndYield")
public class Demo07SleepAndYield {
    public static void main(String[] args) throws InterruptedException {
//        testSleepState();
//        TimeUnit.SECONDS.sleep(1);
//        testYield();
        Runnable task1 = () -> {
            int count = 0;
            for (; ; ) {
                System.out.println("---->1 " + count++);
            }
        };
        Runnable task2 = () -> {
            int count = 0;
            for (; ; ) {
                System.out.println(" ---->2 " + count++);
            }
        };
        Thread t1 = new Thread(task1, "t1");
        Thread t2 = new Thread(task2, "t2");
        // t1设置优先级最低
        t1.setPriority(Thread.MIN_PRIORITY);
        // t2设置优先级最高
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
        

    }

    private static void testYield() {
        Thread t1 = new Thread(() -> {
            int count = 0;
            while (true) {
                System.out.println("-->" + count++);
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            int count = 0;
            while (true) {
                // 将cpu执行权让出
                Thread.yield();
                System.out.println("\t\t-->" + count++);
            }
        }, "t2");

        t1.start();
        t2.start();
    }

    private static void testSleepState() {
        Thread lala = new Thread(() -> {
            // 测试sleep的线程状态
            log.debug("sleep after state = {}", Thread.currentThread().getState());
            log.debug("lala thread enter sleep ...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                log.debug("thread sleep is interrupt");
//                e.printStackTrace();
            }

        }, "拉拉");

        lala.start();
        // 获取睡眠中线程的状态
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("enter sleep thread state = {}", lala.getState());

        // 打断睡觉的线程
        lala.interrupt();
    }
}
