/**
 * description:  Synchronized的锁对象
 * date:         2022/9/4 12:06
 * author        ZhuJunfei
 */

package com.zjf.chapter02threadsafe;

import lombok.extern.slf4j.Slf4j;

public class Demo05SynchronizedLockObject {
    public static void main(String[] args) {
//        Number number = new Number();
//        number.testPractice1();
        double num = 2.8284271247461903 * 2.8284271247461903;
        double eighteen = Math.sqrt(8.0);

        System.out.println(num);

    }
}

@Slf4j(topic = "c.Number")
class Number {
    public synchronized void a() {  // lock object is `this`, will output 12 or 21
        log.debug("1");
    }

    public synchronized void b() {
        log.debug("2");
    }

    public void testPractice1() {
        Number n1 = new Number();
        new Thread(() -> {
            n1.a();
        }).start();
        new Thread(() -> {
            n1.b();
        }).start();
    }
}





