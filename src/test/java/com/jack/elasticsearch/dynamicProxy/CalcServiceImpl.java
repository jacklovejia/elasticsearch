package com.jack.elasticsearch.dynamicProxy;

public class CalcServiceImpl implements CalcService{
    @Override
    public Integer add(Integer a, Integer b) {
        return a+b;
    }
}
