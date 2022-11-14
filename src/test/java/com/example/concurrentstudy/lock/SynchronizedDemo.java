package com.example.concurrentstudy.lock;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @PROJECT_NAME: concurrentstudy
 * @DESCRIPTION: leetcode
 * @USER: renxiang
 * @DATE: 2022/11/13 16:59
 */
@SpringBootTest
public class SynchronizedDemo {
    private static int count = 10000;
    private Object o = new Object();

    @Test
    void test() {
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                test1();
            }).start();
            new Thread(() -> {
                test1();
            }).start();
            new Thread(() -> {
                test1();
            }).start();
        }
    }

    @Test
    void test1() {
        synchronized (o) {
            count--;
            System.out.println("Thread:" + Thread.currentThread().getName() + "count=" + count);
        }
    }

    @Test
    void test2() {
        synchronized (this) {
            count--;
            System.out.println("Thread:" + Thread.currentThread().getName() + "count=" + count);
        }
    }

    @Test
    synchronized void test3() {
        count--;
        System.out.println("Thread:" + Thread.currentThread().getName() + "count=" + count);
    }

    @Test
    static void tesMM() {
        synchronized (SynchronizedDemo.class) {
            count--;
            System.out.println("Thread:" + Thread.currentThread().getName() + "count=" + count);
        }
    }

    @Test
    synchronized static void test4() { //等同于synchronized(T.class)
        count--;
        System.out.println("Thread:" + Thread.currentThread().getName() + "count=" + count);
    }
}
