package com.demo;

/**
 * @author: yong.peng
 * @create: 2023/10/26 14:36
 **/

import java.util.Date;

public class Demo1 extends Date {
    public static void main(String[] args) {
        new Demo1().test();
    }

    public void test() {
        System.out.println(super.getClass().getName());
    }
}