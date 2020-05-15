package com.jack.elasticsearch.loadBalance;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class 动态加权轮询算法 {
    public static Map<String, Weight> serverMap = new HashMap<>();
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(loadBalance());
        }

    }

    public static String loadBalance() {
        Map<String, Integer> ipsMap = new HashMap<>();
        ipsMap.put("A", 5);
        ipsMap.put("B", 3);
        ipsMap.put("C", 2);
        Integer totalWeight = ipsMap.values().stream().reduce(0, (v1, v2) -> v1 + v2);


        /**
         * 加权轮询算法的核心在于
         * 1.每次选出来的服务权重值需要 减去 所有权重之和
         * 2.所有服务的现有权重值加上各自的服务权重值
         * 3.再次选中, 选中后在减去所有权重之和, 反复
         */

        // 1. 初始化 服务 装载数据
        if (serverMap.size() == 0) {
            ipsMap.forEach((k, v) -> {
                serverMap.put(k, new Weight(k, v, v));
            });
        }
        // 2. 初始化一个最大的服务
        Weight maxweight = null;
        // 3. 选出当前最大权重服务
        Set<String> ips = serverMap.keySet();
        for (String ip : ips) {
            Weight indexWeight = serverMap.get(ip);
            if (maxweight == null || maxweight.getCurrentWeight() < indexWeight.getCurrentWeight()) {
                // 找到当前最大的
                maxweight = indexWeight;

            }
        }
        // 4. 修改找到最大的权重值
        maxweight.setCurrentWeight(maxweight.getCurrentWeight() - totalWeight);
        // 5. 所有的服务均加当前的各自对应的权重值
        for (String ip : ips) {
            Weight indexWeight = serverMap.get(ip);
            indexWeight.setCurrentWeight(indexWeight.getCurrentWeight() + indexWeight.getWeight());
        }
        return maxweight.getIp();
    }


    /**
     * 封装一个服务ip权重类
     */
    @Data
    @AllArgsConstructor
    static class Weight {
        // 服务ip
        private String ip;
        // 设置权重值
        private Integer weight;
        // 当前权重
        private Integer currentWeight;
    }
}
