package com.suyang.controller;

import com.suyang.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SuppressWarnings("ALL")
@RestController
public class HelloController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/hello")
    public String hello() {
        taskService.doTaskOne();
        taskService.doTaskTwo();
        taskService.doTaskThree();
        return "finish";
    }

    @GetMapping("/hello2")
    public String asyncHello(String name) {
        CompletableFuture<String> r1 = taskService.doTaskCallBackOne(name);
        CompletableFuture<String> r2 = taskService.doTaskCallBackTwo(name);
        CompletableFuture<String> r3 = taskService.doTaskCallBackThree(name);

        CompletableFuture.allOf(r1, r2, r3).join();

        try {
            return r1.get() + " " + r2.get() + " " + r3.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "";
    }

    @GetMapping("/hello3")
    public Callable<String> asyncHello2(String name) {
        return () -> {
            CompletableFuture<String> r1 = taskService.doTaskCallBackOne(name);
            CompletableFuture<String> r2 = taskService.doTaskCallBackTwo(name);
            CompletableFuture<String> r3 = taskService.doTaskCallBackThree(name);

            CompletableFuture.allOf(r1, r2, r3).join();

            try {
                return r1.get() + " " + r2.get() + " " + r3.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return "";
        };
    }
}
