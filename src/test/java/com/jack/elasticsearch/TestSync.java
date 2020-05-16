package com.jack.elasticsearch;

import java.util.concurrent.CyclicBarrier;

public class TestSync {

    public static void main(String[] args) {

    }

    /**
     * 两个线程交替打印1-100
     */
    public static void test01() {
        Object o1 = new Object();
        Object o2 = new Object();
        Thread a = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                synchronized (o1) {
                    o1.notify();
                    System.out.println(i);
                    i++;
                    try {
                        o1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "A");

        Thread b = new Thread(() -> {
            for (int i = 1; i < 100; i++) {
                synchronized (o1) {
                    o1.notify();
                    System.out.println(i);
                    i++;
                    try {
                        o1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "B");
        a.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        b.start();


    }
}
