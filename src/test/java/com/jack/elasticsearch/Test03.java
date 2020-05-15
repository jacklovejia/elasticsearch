package com.jack.elasticsearch;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

public class Test03 {

   static Map<String, Weight> map = new HashMap<>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {

            System.out.println(loadbalace01());
        }

    }

    private static String loadbalace01() {
        Map<String, Integer> ipsConfigMap = new HashMap<>();
        ipsConfigMap.put("A", 5);
        ipsConfigMap.put("B", 3);
        ipsConfigMap.put("C", 2);

        int sum = 10;

        // 轮询权重算法
        // 1.初始化
        if(0 == map.size()){
            ipsConfigMap.forEach((k, v) -> map.put(k, new Weight(k, v, v)));
        }
        // 2.找到最大的
        Weight maxWeight = null;
        for (String s : map.keySet()) {
            if (maxWeight == null || maxWeight.currentWeight < map.get(s).getCurrentWeight())
                maxWeight = map.get(s);
        }
        // 3.最大的权重重新设置
        maxWeight.setCurrentWeight(maxWeight.currentWeight - sum);
        // 4.全部提升各自权重
        map.forEach((k,v)->{
            v.setCurrentWeight(v.currentWeight+ipsConfigMap.get(k));
        });
        // 5.返回
        return maxWeight.ip;
    }

    @Data
    @AllArgsConstructor
    static
    class Weight {
        String ip;
        Integer weight;
        Integer currentWeight;
    }
}
