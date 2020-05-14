package com.jack.elasticsearch.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UserServiceProxy implements InvocationHandler {
    private Object object;

    public UserServiceProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("用户服务: 入参: "+ args[0].toString());
        System.out.println(method);
        MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
        System.out.println(annotation);
        System.out.println(annotation.value());
        Object invoke = method.invoke(object, args);
        System.out.println("用户服务: 出参: "+ invoke);
        return invoke;
    }
}
