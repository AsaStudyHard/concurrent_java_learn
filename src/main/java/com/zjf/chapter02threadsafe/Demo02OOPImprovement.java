/**
 * description:  使用面向对象进行改进
 * date:         2022/8/30 9:04
 * author        ZhuJunfei
 */

package com.zjf.chapter02threadsafe;

import lombok.extern.slf4j.Slf4j;

class Calculator {
    int value = 0;

    public void increment() {
        synchronized (this) {
            value++;
        }
    }

    public void decrement() {
        synchronized (this) {
            value--;
        }
    }

    public int get() {
        synchronized (this) {
            return value;
        }
    }
}

@Slf4j(topic = "c.Demo02OOPImprovement")
public class Demo02OOPImprovement {
    public static void main(String[] args) throws InterruptedException {
        Calculator calc = new Calculator();
        Thread t1 = new Thread(() -> {
            for (int j = 0; j < 5000; j++) {
                calc.increment();
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                calc.decrement();
            }
        }, "t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        log.debug("counter = {}", calc.get());
    }
}
