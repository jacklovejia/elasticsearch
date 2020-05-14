package com.jack.elasticsearch.utils;

import java.util.Map;

public class ThreadLocalUtils {
    private static ThreadLocal<Map<String,Object>> testThreadLocal = new ThreadLocal<Map<String, Object>>();

    private ThreadLocalUtils() {}
    public static Map<String, Object> getUserInfoLocalParam(){
        return testThreadLocal.get();
    }

    public static void setUserInfoLocalParam(Map<String,Object> map){
        testThreadLocal.set(map);
    }

    public static void clearUserInfoLocalParam(){
        testThreadLocal.remove();
    }
}
