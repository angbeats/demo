package com.my.qs.designpatterns.proxy.staticproxy;

/**
 * @description: 静态代理
 * @author: angbeats
 * @create: 2021-01-07 13:42
 **/

public class UserDaoProxy implements IUserDao{

    private IUserDao userDao;


    public UserDaoProxy(IUserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public void findUser() {
        System.out.println("before...");
        this.userDao.findUser();
        System.out.println("after...");
    }

    public static void main(String[] args) {
        IUserDao userDao = new UserDao();
        UserDaoProxy userDaoProxy = new UserDaoProxy(userDao);
        userDaoProxy.findUser();
    }
}