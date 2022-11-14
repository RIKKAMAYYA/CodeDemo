package com.example.concurrentstudy.lock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.dc.pr.PRError;

/**
 * @PROJECT_NAME: concurrentstudy
 * @DESCRIPTION:
 * @USER: renxiang
 * @DATE: 2022/11/13 17:33
 */
@SpringBootTest
@NoArgsConstructor
@Data
public class WriteWithLockReadNoLock {
    private int balance;
    private String name;

    /**
     * 容易脏读, 解决办法就是 读取也加锁
     */
    private synchronized void setMoney (int balance) throws InterruptedException {
        Thread.sleep(2000);
        this.balance = balance;
    }

    private synchronized int getMoney () {
        return this.balance;
    }

    @Test
    void test() throws InterruptedException {
        WriteWithLockReadNoLock w = new WriteWithLockReadNoLock();
        new Thread(()-> {
            try {
                w.setMoney(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } ).start();
        Thread.sleep(2000);
        System.out.println(w.getMoney());
        Thread.sleep(1500);
        System.out.println(w.getMoney());
    }
}
