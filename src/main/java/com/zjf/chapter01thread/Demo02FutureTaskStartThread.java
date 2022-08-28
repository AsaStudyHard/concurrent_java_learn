/**
 * description:  future task 作为线程任务进行执行
 * date:         2022/8/28 15:24
 * author        ZhuJunfei
 */

package com.zjf.chapter01thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j(topic = "c.Demo02FutureTaskStartThread")
public class Demo02FutureTaskStartThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Callable也是一个FunctionInterface, 可以使用lambda 进行简化,
        // 他的泛型数据类型是表示他的返回值, 需要和FutureTask相同
        Callable<Integer> callable = () -> {
            log.debug("future taskrunning...");
            return 2333;
        };

        FutureTask<Integer> futureTask = new FutureTask<>(callable);

        Thread t1 = new Thread(futureTask, "t1");
        t1.start();
        // FutureTask 提供了get方法 从而获取我们的线程执行任务完成后的返回值, 他会一直在main thread 中进行阻塞
        log.debug("{}", futureTask.get());
    }
}
