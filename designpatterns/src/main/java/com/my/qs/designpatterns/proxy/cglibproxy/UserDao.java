package com.my.qs.designpatterns.proxy.cglibproxy;

import com.my.qs.designpatterns.proxy.jdkproxy.IUserDao;

/**
 * @description:
 * @author: angbeats
 * @create: 2021-01-07 13:41
 **/

public class UserDao implements IUserDao {

    @Override
    public void findUser() {
        System.out.println("findUser from db");
    }
}