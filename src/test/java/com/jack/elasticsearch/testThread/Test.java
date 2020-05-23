package com.jack.elasticsearch.testThread;

import java.util.concurrent.CountDownLatch;

public class Test {
    public static void main(String[] args) throws InterruptedException {

        
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(10000);
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1");
        }, "1");

        Thread thread2 = new Thread(() -> {
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            System.out.println("2");
        }, "2");

        thread2.start();
        thread1.start();
        countDownLatch.await();
        System.out.println("ok");
    }
}
