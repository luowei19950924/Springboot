package com.example.springboot.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * 异步调用
 *
 * @author luowei
 * @date 2019/5/8 19:53
 */
public class AsyncServiceImpl implements AsyncService {

    private static Random random = new Random();

    @Async //异步
    @Override
    public Future<String> doTask1() {
        try {
            System.out.println("任务一开始");
            long start = System.currentTimeMillis();
            Thread.sleep(random.nextInt(10000));
            long end = System.currentTimeMillis();
            System.out.println("任务一结束，耗时：" + (end - start) + "毫秒");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AsyncResult<>("任务一结束");
    }

    @Async
    @Override
    public Future<String> doTask2() {
        try {
            System.out.println("任务二开始");
            long start = System.currentTimeMillis();
            Thread.sleep(random.nextInt(10000));
            long end = System.currentTimeMillis();
            System.out.println("任务二结束，耗时：" + (end - start) + "毫秒");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AsyncResult<>("任务二结束");
    }

    @Async
    @Override
    public Future<String> doTask3() {
        try {
            System.out.println("任务三开始");
            long start = System.currentTimeMillis();
            Thread.sleep(random.nextInt(10000));
            long end = System.currentTimeMillis();
            System.out.println("任务三结束，耗时：" + (end - start) + "毫秒");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AsyncResult<>("任务三结束");
    }
}
