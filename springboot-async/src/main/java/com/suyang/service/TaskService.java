package com.suyang.service;

import java.util.concurrent.CompletableFuture;

public interface TaskService {
    void doTaskOne();

    void doTaskTwo();

    void doTaskThree();

    CompletableFuture<String> doTaskCallBackOne(String name);

    CompletableFuture<String> doTaskCallBackTwo(String name);

    CompletableFuture<String> doTaskCallBackThree(String name);
}
