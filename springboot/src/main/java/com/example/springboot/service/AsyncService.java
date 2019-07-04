package com.example.springboot.service;

import java.util.concurrent.Future;

/**
 * @author luowei
 * @date 2019/5/8 19:53
 */
public interface AsyncService {

    Future<String> doTask1();
    Future<String> doTask2();
    Future<String> doTask3();
}
