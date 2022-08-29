/**
 * description:  6种线程状态的测试
 * date:         2022/8/29 20:30
 * author        ZhuJunfei
 */

package com.zjf.chapter01thread;

import com.zjf.util.FileReader;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

import static com.zjf.chapter01thread.Constants.FILE_FULL_PATH;


@Slf4j(topic = "c.Demo12ThreadStateCase")
public class Demo12ThreadStateCase {
    static Class lock = Demo12ThreadStateCase.class;

    public static void testReadFile(){
        FileReader.read(FILE_FULL_PATH);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread new_thread = new Thread(() -> {

        }, "new_thread");

        Thread running_th = new Thread(() -> {
            // 死循环测试状态
            while(true){}
        }, "running_th");

        Thread running_io_th = new Thread(() -> {
            testReadFile();
        }, "running_io_th");

        Thread time_wait_th = new Thread(() -> {
            synchronized (lock){
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "time_wait_th");

        Thread wait_th = new Thread(() -> {
            try {
                running_th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "wait_th");

        Thread blocked_th = new Thread(() -> {
            synchronized (lock){
                log.debug("....");
            }
        }, "blocked_th");

        // start thread
//        new_thread.start();
        running_th.start();
        running_io_th.start();
        time_wait_th.start();
        wait_th.start();
        blocked_th.start();

        // 主线程睡一会, 让所有子线程全部执行完
        TimeUnit.MILLISECONDS.sleep(400);

        // 获取线程状态
        log.debug("new_thread state: {}", new_thread.getState());
        log.debug("running_th state: {}", running_th.getState());
        log.debug("running_io_th state: {}", running_io_th.getState());
        log.debug("wait_th state: {}", wait_th.getState());
        log.debug("time_wait_th state: {}", time_wait_th.getState());
        log.debug("blocked_th state: {}", blocked_th.getState());
    }
}
