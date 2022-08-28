/**
 * description:  在linux中查看线程状态
 * date:         2022/8/28 15:58
 * author        ZhuJunfei
 */

package com.zjf.chapter01thread;

public class Demo03LinuxPeekThread {

    public static void main(String[] args) {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("extends thread running");
                }
            }
        };
        t1.start();
        Thread t2 = new Thread(() -> {
            while (true){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("implement runnable running");
            }
        }, "t2");
        t2.start();
    }
}
