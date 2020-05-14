package com.jack.elasticsearch.test;

import java.lang.reflect.Proxy;

public class TestUserServiceProxy {
    public static void main(String[] args) {
        // 目标对象
        UserService userService = new UserServiceImpl();
        // 代理类
        UserServiceProxy userServiceProxy = new UserServiceProxy(userService);
        // 代理出来
        UserService users = (UserService) Proxy.newProxyInstance(userServiceProxy.getClass().getClassLoader(),
                userService.getClass().getInterfaces(),
                userServiceProxy);
        System.out.println(users.getUserName("1"));
    }
}
