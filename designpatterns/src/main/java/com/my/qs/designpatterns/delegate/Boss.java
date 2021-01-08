package com.my.qs.designpatterns.delegate;

/**
 * @description:
 * @author: angbeats
 * @create: 2021-01-08 11:21
 **/

public class Boss {

    private Leader leader;

    public Boss(Leader leader) {
        this.leader = leader;
    }

    public void sendWork(String work){
        this.leader.work(work);
    }

    public static void main(String[] args) {
        Boss boss = new Boss(new Leader());
        boss.sendWork("b");
    }
}