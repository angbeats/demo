package com.my.qs.designpatterns.delegate;

/**
 * @description:
 * @author: angbeats
 * @create: 2021-01-08 11:27
 **/

public class WorkerB implements Worker {

    @Override
    public void work() {
        System.out.println("do B work...");
    }
}