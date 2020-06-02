package com.jack.elasticsearch.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/jvm")
public class TestJVM {

    /**
     * 死锁
     */
    @RequestMapping("/lock")
    public void lock() {

        final Object object1 = new Object();
        final Object object2 = new Object();

        new Thread(() -> {
            synchronized (object1) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object2) {
                    log.info("线程1");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (object2) {
                synchronized (object1) {
                    log.info("线程2");
                }
            }
        }).start();


    }

    /**
     * cpu 负载高
     */
    @RequestMapping("/cpu")
    public void cpu() {
        while (true) {

        }
    }

    /**
     * 内存泄露
     */
    @RequestMapping("/xielou")
    public void xielou() {
        List<String> list = new ArrayList<>();
        while (1 == 1) {
            list.add("1");
        }
    }


}
