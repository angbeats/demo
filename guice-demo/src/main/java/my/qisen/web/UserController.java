package my.qisen.web;

import my.qisen.entity.User;
import my.qisen.service.UserService;

import javax.inject.Inject;

/**
 * @description:
 * @author: angbeats
 * @create: 2020-12-10 11:05
 **/

public class UserController {

    private UserService userService;

    @Inject
    public UserController(UserService userService){
        this.userService = userService;
    }

    public User getUser(){
        return userService.getUser();
    }

}