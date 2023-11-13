package com.demo;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: yong.peng
 * @create: 2023/7/24 16:46
 **/

public class StreamTest {

//    public static void main(String[] args) {
//        List<String> list1 = Arrays.asList("m,k,l,a", "1,3,5,7");
//        List<String> listNew = list1.stream().flatMap(s -> {
//            // 将每个元素转换成一个stream
//            String[] split = s.split(",");
//            return Arrays.stream(split);
//        }).collect(Collectors.toList());
//        System.out.println("处理前的集合：" + list1);
//        System.out.println("处理后的集合：" + listNew);
//    }

    public static Hero build(String name,int damage,String type){
        return new Hero(name,damage,type);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Hero{
        private String name;
        private int damage;
        private String type;
    }

    public static void main(String[] args) {

        List<Hero> heroes = Arrays.asList(StreamTest.build("蛮王",120,"战士"),
            StreamTest.build("辛德拉",60,"法师"),
            StreamTest.build("赵信",110,"战士"),
            StreamTest.build("男刀",100,"刺客"),
            StreamTest.build("冰霜女巫",66,"法师"),
            StreamTest.build("刀妹",108,"刺客"),
            StreamTest.build("蒙多",99,"战士"));

        //groupingByConcurrent与此相似，只是最终收集到ConcurrentMap中
        //按英雄类别分类，并添加到各类集合中
        Map<String,List<Hero>> heroList = heroes.stream().collect(Collectors.groupingBy(Hero::getType));
        System.out.println("分类后的英雄列表：\n"+ heroList);
        System.out.println();

        //按英雄类别分类，求各类英雄的平均战力
        Map<String,Double> damageMap = heroes.stream().collect(Collectors.groupingBy(Hero::getType,Collectors.averagingInt(Hero::getDamage)));
        System.out.println("分类后的英雄平均战力：\n"+damageMap);
        System.out.println();

        //按英雄类别分类，求各类英雄个数，并装入指定类型的集合TreeMap
        TreeMap<String,Long> treeMap = heroes.stream().collect(Collectors.groupingBy(Hero::getType,TreeMap::new,Collectors.counting()));
        System.out.println("分类后的各类英雄个数：\n"+treeMap);
        System.out.println();
    }

}
