package com.example.concurrentstudy.lock;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @PROJECT_NAME: concurrentstudy
 * @DESCRIPTION: leetcode   可重入锁
 * @USER: renxiang
 * @DATE: 2022/11/13 17:52
 */
@SpringBootTest
public class ReentrantLockT {

    @Test
    void test() {
        new T().test1();
    }

    synchronized void test1() {
        System.out.println("m start");
        System.out.println("m end");
    }

    class T extends ReentrantLockT {
        @Override
        synchronized void test1() { //锁的T this对象
            System.out.println("child start");
            super.test1(); //锁的T this对象，和上面一样，可重入锁
            System.out.println("child end");
        }
    }

}
