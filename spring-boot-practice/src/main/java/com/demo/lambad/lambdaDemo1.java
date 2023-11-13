package com.demo.lambad;

import java.util.function.IntConsumer;

/**
 * @author: yong.peng
 * @create: 2023/8/1 10:48
 **/

public class lambdaDemo1 {
    public static void main(String[] args) {


        foreachArr(new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println(value);
            }
        });

        foreachArr(System.out::println);

    }


    public static void foreachArr(IntConsumer consumer) {
        int[] arr = {1, 2, 3, 4, 5};
        for (int i : arr) {
            consumer.accept(i);
        }
    }

}
