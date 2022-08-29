/**
 * description:  2阶段终止模式的实现
 * date:         2022/8/29 16:21
 * author        ZhuJunfei
 */

package com.zjf.chapter01thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j(topic = "c.Demo10TwoPhaseTermination")
public class Demo10TwoPhaseTermination {
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination.start();
        TwoPhaseTermination.start();
        TwoPhaseTermination.start();

        TimeUnit.SECONDS.sleep(5);

        TwoPhaseTermination.end();
        TwoPhaseTermination.end();
        TwoPhaseTermination.end();
    }

}

@Slf4j(topic = "c.TwoPhaseTermination")
class TwoPhaseTermination{
    private static Thread monitor;
    // 防止启动多个线程
    private static Boolean isStart = false;
    // 防止打断多次
    private static Boolean isEnd = false;


    public static void start() {
        if(isStart){
            // 代码执行至此, 表示已经启动过
           return;
        }
        isStart = true;

        monitor = new Thread(() -> {
            while (true) {
                Thread currentThread = Thread.currentThread();
                if (currentThread.isInterrupted()) {
                    // 代码执行至此, 表示我们的线程被打断了, 结束线程, 收尾了
                    log.debug("收尸了, 不陪你们玩啦, 下辈子要过的开心啊...");
                    break;
                }

                // 代码执行至此, 防止cpu负载过重, 让线程睡一会
                try {
                    TimeUnit.SECONDS.sleep(1);
                    // 执行员工的桌面监控
                    log.debug("你用电脑注意点, 我正在看你哦~");

                } catch (InterruptedException e) {
                    // 线程被打断, 需要去重新设置打断标记
                    currentThread.interrupt();
                    log.debug("线程被打断了, 要退出程序了");
                }
            }
        }, "monitor");
        monitor.start();
    }

    public static void end() {
        if (isEnd){
            // 代码执行至此表示已经打断过了
            return;
        }
        isEnd = true;
        // 打断线程
        monitor.interrupt();
        log.debug("线程已经被打断了...");
    }
}

