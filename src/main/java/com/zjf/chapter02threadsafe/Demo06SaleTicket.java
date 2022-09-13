/**
 * description:  售票案例
 * date:         2022/9/4 16:51
 * author        ZhuJunfei
 */

package com.zjf.chapter02threadsafe;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

@Slf4j(topic = "c.Demo06SaleTicket")
public class Demo06SaleTicket {

    public static void main(String[] args) {
        // 初始2000张票
        TicketWindow ticketWindow = new TicketWindow(2000);
        // 窗口的容器
        List<Thread> list = new ArrayList<>();
        // 用来存储买出去多少张票, Vector是List的线程安全的实现
        List<Integer> sellCount = new Vector<>();

        for (int i = 0; i < 2000; i++) {
            Thread t = new Thread(() -> {
                // 分析这里的竞态条件, TicketWindow中的sell操作不是Atomicity, 所以他的count -= amount是 race condition
                int count = ticketWindow.sell(randomAmount());
                sellCount.add(count);
            });

            list.add(t);
            t.start();
        }

        // 等待所有的线程执行完毕
        list.forEach((t) -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 买出去的票求和, mapToInt 将map转为 int, 然后sum()求和
        log.debug("sold count:{}", sellCount.stream().mapToInt(c -> c).sum());
        // 剩余票数
        log.debug("remainder count:{}", ticketWindow.getCount());
    }

    // Random 为线程安全
    static Random random = new Random();

    // 随机 1~5
    public static int randomAmount() {
        return random.nextInt(5) + 1;
    }
}


class TicketWindow {
    private int count;

    public TicketWindow(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    /*public int sell(int amount) {
        if (this.count >= amount) {
            this.count -= amount;
            return amount;
        } else {
            return 0;
        }
    }*/
    // 解决方案
    public synchronized int sell(int amount) {
        if (this.count >= amount) {
            this.count -= amount;
            return amount;
        } else {
            return 0;
        }
    }
}
