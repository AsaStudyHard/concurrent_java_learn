/**
 * description:  线程变量的安全分析
 * date:         2022/8/30 14:10
 * author        ZhuJunfei
 */

package com.zjf.chapter02threadsafe;

import lombok.extern.slf4j.Slf4j;


import java.util.ArrayList;

@Slf4j(topic = "c.Demo04ThreadSafeAnalysis")
public class Demo04ThreadSafeAnalysis {
    public static final int THREAD_COUNT = 2;
    public static final int LOOP_COUNT = 200;

    public static void main(String[] args) {
        ThreadUnSafe threadUnSafe = new ThreadUnSafe();
        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread(() -> {
                log.debug("running...");
                threadUnSafe.IncrementAndDecrement(LOOP_COUNT);
            }, "Thread_" + i).start();

        }

    }
}

@Slf4j(topic = "c.ThreadUnSafe")
class ThreadUnSafe {
    ArrayList<Integer> arr = new ArrayList<>();

    public void IncrementAndDecrement(int loopCount) {
        for (int i = 0; i < loopCount; ++i) {
            increment();
            decrement();
        }
    }

    private void increment() {
        arr.add(1);
    }

    private void decrement() {
        arr.remove(0);
    }
}

