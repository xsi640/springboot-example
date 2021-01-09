package com.suyang.service.impl;

import com.suyang.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    private Random random = new Random();

    @Override
    public void doTaskOne() {
        log.info("开始做任务一");
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(random.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.info("完成任务一，耗时：" + (end - start) + "毫秒");
    }

    @Override
    public void doTaskTwo() {
        log.info("开始做任务二");
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(random.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.info("完成任务二，耗时：" + (end - start) + "毫秒");
    }

    @Override
    public void doTaskThree() {
        log.info("开始做任务三");
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(random.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.info("完成任务三，耗时：" + (end - start) + "毫秒");
    }

    @Override
    public CompletableFuture<String> doTaskCallBackOne(String name) {
        log.info("开始做任务一（带回调）");
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(random.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.info("完成任务一（带回调），耗时：" + (end - start) + "毫秒");
        return CompletableFuture.completedFuture("hello, " + name);
    }

    @Override
    public CompletableFuture<String> doTaskCallBackTwo(String name) {
        log.info("开始做任务二（带回调）");
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(random.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.info("完成任务二（带回调），耗时：" + (end - start) + "毫秒");
        return CompletableFuture.completedFuture("hello, " + name);
    }

    @Override
    public CompletableFuture<String> doTaskCallBackThree(String name) {
        log.info("开始做任务三（带回调）");
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(random.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.info("完成任务三（带回调），耗时：" + (end - start) + "毫秒");
        return CompletableFuture.completedFuture("hello, " + name);
    }
}
