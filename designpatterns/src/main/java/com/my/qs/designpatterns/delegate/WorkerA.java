package com.my.qs.designpatterns.delegate;

/**
 * @description:
 * @author: angbeats
 * @create: 2021-01-08 11:25
 **/

public class WorkerA implements Worker {

    @Override
    public void work() {
        System.out.println("do A work...");
    }
}