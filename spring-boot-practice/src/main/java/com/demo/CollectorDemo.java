package com.demo;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.logging.log4j.util.PropertySource.Comparator;

/**
 * @author: yong.peng
 * @create: 2023/7/12 20:39
 **/

public class CollectorDemo {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 3, 8, 5, 2, 9, 4, 7, 6);

        //summarizingDouble、summarizingLong与此相似
        IntSummaryStatistics intSummaryStatistics = list.stream()
            .collect(Collectors.summarizingInt(s -> s));
        System.out.println("IntSummaryStatistics求和：" + intSummaryStatistics.getSum());
        System.out.println("IntSummaryStatistics求平均值：" + intSummaryStatistics.getAverage());
        System.out.println("IntSummaryStatistics求个数：" + intSummaryStatistics.getCount());
        System.out.println("IntSummaryStatistics求最大值：" + intSummaryStatistics.getMax());
        System.out.println("IntSummaryStatistics求最小值：" + intSummaryStatistics.getMin());
        System.out.println();

        //summingDouble、summingLong与此相似
        int i = list.stream().collect(Collectors.summingInt(s -> s));
        System.out.println("summingInt求和：" + i);

        long num = list.stream().collect(Collectors.counting());
        System.out.println("counting求个数：" + num);
        System.out.println();


        //averagingDouble、averagingLong与此相似
        double avg = list.stream().collect(Collectors.averagingInt(s -> s));
        System.out.println("averagingInt求平均值：" + avg);
    }
}
