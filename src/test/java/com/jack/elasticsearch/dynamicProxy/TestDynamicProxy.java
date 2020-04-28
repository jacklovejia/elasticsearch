package com.jack.elasticsearch.dynamicProxy;

import java.lang.reflect.Proxy;

public class TestDynamicProxy {
    public static void main(String[] args) {
        /**
         *         CalcService 是接口
         *         CalcServiceImpl 是实现类
         *         DynamicProxy 是中介 代理类
         *         main 测试
         */
        DynamicProxy d = new DynamicProxy(new CalcServiceImpl());

        CalcService c = (CalcService) Proxy.newProxyInstance(CalcService.class.getClassLoader(), new Class[]{CalcService.class}, d);
        Integer add = c.add(1, 1);
        System.out.println(add);

    }
}
