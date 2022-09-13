/**
 * description:  转账
 * date:         2022/9/4 17:14
 * author        ZhuJunfei
 */

package com.zjf.chapter02threadsafe;

import lombok.extern.slf4j.Slf4j;

import static com.zjf.chapter02threadsafe.Demo06SaleTicket.randomAmount;

@Slf4j(topic = "c.Demo07Transfer")
public class Demo07Transfer {
    public static void main(String[] args) throws InterruptedException {
        Account a = new Account(1000);
        Account b = new Account(1000);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                // transfer出现race condition,
                a.transfer(b, randomAmount());
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                b.transfer(a, randomAmount());
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // 查看转账2000次后的总金额
        log.debug("total:{}", (a.getMoney() + b.getMoney()));
        log.debug("a money = {}", a.getMoney());
        log.debug("b money = {}", b.getMoney());
    }


}


class Account {
    private static final Object lock = new Object();
    private int money;

    public Account(int money) {
        this.money = money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return this.money;
    }

    public void transfer(Account target, int amount) {
        synchronized (lock) {
            if (this.money >= amount) {
                this.money -= amount;
                // 越来越多: 我们getMoney() 在对方还没开始 -=的时候获取, 获取完成对方 -=, 然后我们set,将对方的钱变多了
                // 越来越少: 我们进入了if 判断后, 获取了 -= money的值, 即将赋值的时候, 丢失cpu执行权, 对方将我们的money set, 然后我们将set后的money给覆盖了
                target.setMoney(target.getMoney() + amount);
            }
        }
    }
}