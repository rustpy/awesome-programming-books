package com.demo.lambad;

import com.model.modelOne;
import com.sun.org.apache.xpath.internal.operations.Equals;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: yong.peng
 * @create: 2023/8/1 10:48
 **/

public class lambdaDemo1 {
    public static void main(String[] args) {

        foreachArr(System.out::println);

    }

//        foreachArr(new IntConsumer() {
//            @Override
//            public void accept(int value) {
//                System.out.println(value);
//            }
//        });
//
//        foreachArr(System.out::println);
//
//    }

    public static void foreachArr(IntConsumer consumer) {
        int[] arr = {1, 2, 3, 4, 5};
        for (int i : arr) {
            consumer.accept(i);
        }
    }

    private static void test01() {
        Map<String,Integer> map = new HashMap<>();
        map.put("蜡笔小新",6);
        map.put("test1",7);

        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        Stream<Entry<String, Integer>> stream = entrySet.stream();

        stream.filter(entry -> entry.getValue() > 6).forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));

        List<modelOne> listOne = new ArrayList<>();
        listOne.add(new modelOne("张三","大学生","高等数学"));

    }


}
