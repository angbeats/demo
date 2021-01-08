package com.my.qs.designpatterns.delegate;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: angbeats
 * @create: 2021-01-08 11:22
 **/

public class Leader {

    private Map<String, Worker> map = new HashMap<>();

    public Leader() {
        map.put("a", new WorkerA());
        map.put("b", new WorkerB());
    }

    public void work(String work){
        map.get(work).work();
    }
}