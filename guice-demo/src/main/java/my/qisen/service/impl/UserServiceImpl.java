package my.qisen.service.impl;

import my.qisen.entity.User;
import my.qisen.service.UserService;

/**
 * @description: UserService实现类
 * @author: angbeats
 * @create: 2020-12-10 10:47
 **/

public class UserServiceImpl implements UserService {

    @Override
    public User getUser() {
        User user = new User();
        user.setUsername("aaa")
                .setPassword("bbb");
        return user;
    }
}