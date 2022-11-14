package com.example.concurrentstudy.thread;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.dc.pr.PRError;

import java.util.concurrent.*;

/**
 * @PROJECT_NAME: concurrentstudy
 * @DESCRIPTION: leetcode
 * @USER: renxiang
 * @DATE: 2022/11/13 15:50
 */
@SpringBootTest
public class ThreadMethod {

    @Test
    void testYield() {
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("A" + i);
                if (i % 10 == 0) {
                    Thread.yield();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("B" + i);
                if (i % 10 == 0) {
                    Thread.yield();
                }
            }
        }).start();
    }

    /**
     * 到某一结点让其他线程运行，运行完了，再回来运行 顺序打印abc
     */
    @Test
    void testJoin() throws Exception {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("a");
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    t1.join();
                    System.out.println("b");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        Thread t3 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    t2.join();
                    System.out.println("c");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        /*A a = new A();
        Thread b = new Thread(new B());
        FutureTask ft = new FutureTask<>(new C());
        Thread c = new Thread(ft);

        for (int i = 0; i < 10; i++) {
            //join 保证该线程运行完毕才到其他线程
            a.start();
            a.join();
            b.start();
            b.join();
            c.start();
            c.join();
            a = new A();
            b = new Thread(new B());
            c = new Thread(new FutureTask<>(new C()));
        }*/

        //ExecutorService pool = Executors.newSingleThreadExecutor();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1,
                1,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );

        for (int i = 0; i < 10; i++) {
            pool.execute(new A());
            pool.execute(new B());
            pool.submit(new C());
        }
        pool.shutdown();

    }

    public class A extends Thread {
        @Override
        public void run() {
            System.out.print("A");
        }
    }

    public class B implements Runnable {
        @Override
        public void run() {
            System.out.print("B");
        }
    }

    public class C implements Callable {
        @Override
        public Object call() throws Exception {
            System.out.print("C");
            return null;
        }
    }

}
