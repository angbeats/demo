package com.my.qs.designpatterns.proxy.jdkproxy;

import sun.misc.ProxyGenerator;

import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @description: jdk动态代理
 * @author: angbeats
 * @create: 2021-01-07 13:50
 **/

public class UserDaoJdkProxy implements InvocationHandler {
    private Object target;

    public UserDaoJdkProxy(Object target) {
        this.target = target;
    }

    public Object getInstance(){
        ClassLoader classLoader = this.target.getClass().getClassLoader();
        Class<?>[] interfaces = this.target.getClass().getInterfaces();

        return Proxy.newProxyInstance(classLoader, interfaces, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?>[] cls = new Class<?>[]{this.target.getClass()};
        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", cls);
        Path path = Paths.get("C:\\Users\\cd\\Desktop\\$Proxy0.class");
        Files.createFile(path);
        OutputStream outputStream = Files.newOutputStream(path, StandardOpenOption.WRITE);
        try {
            outputStream.write(bytes);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            outputStream.close();
        }

        before();
        Object result = method.invoke(target, args);
        after();
        return result;
    }

    public void before(){
        System.out.println("前置工作...");
    }

    public void after(){
        System.out.println("后置处理...");
    }

    public static void main(String[] args) {
        IUserDao userDao = new UserDao();
        UserDaoJdkProxy userDaoJdkProxy = new UserDaoJdkProxy(userDao);
        IUserDao instance = (IUserDao)userDaoJdkProxy.getInstance();
        instance.findUser();
    }
}