package com.jack.elasticsearch.loadBalance;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 一致性哈西算法
 */
public class 一致性哈西环 {

    static  Map<String, Integer> IPMap = new HashMap<>();
    static final Integer vNo = 10;
    static TreeMap<Integer, String> loadBalanceMap = new TreeMap<>();
    static{
        IPMap.put("A",1);
        IPMap.put("B",2);
        IPMap.put("C",3);
    }

    /**
     * 哈西环 主要实现的数据结果采用 treeMap
     */
    private static TreeMap<Integer, String> treeMap = new TreeMap<>();
    static {
        IPMap.forEach((k,v)->{
            for (int i = 0; i < vNo; i++) {
                Integer hash = getHash(k);
                loadBalanceMap.put(hash,k);
                k=k+"-1";
            }
        });
    }
    public static Integer getHash(String ip){
        return ip.hashCode();
    }

    public static void main(String[] args) {
       /* TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(1,"1");
        treeMap.put(3,"2");
        treeMap.put(2,"2");
        // 获取当前红黑树的第一个节点,
        System.out.println(treeMap.firstKey());
        // 获取 节点大于等于2的子树的第一个节点
        System.out.println(treeMap.tailMap(2).firstKey());*/


    }
}
