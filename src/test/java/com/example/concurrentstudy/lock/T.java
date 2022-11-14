package com.example.concurrentstudy.lock;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @PROJECT_NAME: concurrentstudy
 * @DESCRIPTION: leetcode
 * @USER: renxiang
 * @DATE: 2022/11/13 17:15
 */
@SpringBootTest
public class T implements Runnable {
    private int count = 10;

    @Test
    void test() {
        T t = new T();
        for (int i = 0; i < 5; i++) {
            new Thread(t, "THREAD" + i).start();
        }

    }

    @Override
    public void run() {
        count--;
        System.out.println("Thread:" + Thread.currentThread().getName() + "count=" + count);
    }
}
