package com.demo;

import java.text.Collator;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.swing.Spring;

import org.springframework.util.StringUtils;

/**
 * @author: yong.peng
 * @create: 2023/4/4 16:48
 **/

public class Test {
    public static void main(String[] args) {



        List<String> list = Arrays.asList("element1", "element2", "element3");
        String result = String.join(",", list);
        System.out.println(result);

        List<String> request1 = new ArrayList<>();
        System.out.println(request1);
        String result1 = String.join(",", request1);
        System.out.println(result1);


    }
}

