package com.my.qs.designpatterns.proxy.cglibproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @description: Cglib动态代理
 * @author: angbeats
 * @create: 2021-01-07 14:27
 **/

public class UserDaoCglibProxy implements MethodInterceptor {

    private Object target;

    public UserDaoCglibProxy(Object target) {
        this.target = target;
    }

    public Object getInstance(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = method.invoke(this.target, objects);
        after();
        return result;
    }

    public void before(){
        System.out.println("方法执行前...");
    }

    public void after(){
        System.out.println("方法执行后...");
    }

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        UserDaoCglibProxy userDaoCglibProxy = new UserDaoCglibProxy(userDao);
        UserDao instance = (UserDao)userDaoCglibProxy.getInstance();
        instance.findUser();

    }
}