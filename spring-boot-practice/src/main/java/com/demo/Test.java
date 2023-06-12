package com.demo;

import java.text.Collator;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.swing.Spring;


/**
 * @author: yong.peng
 * @create: 2023/4/4 16:48
 **/


public class Test {
    public static void main(String[] args) {

//        List<String> request1 = Arrays.asList("谷歌", "腾讯", "百度", "淘宝");
//        for (String str : request1) {
//            System.out.println(str);
//        }

//        String targetCity = "SHA,BJS";
//        String gpsCity = "AAA";
//        Boolean test = targetCity.contains(gpsCity);
//        if(targetCity.contains(gpsCity)){
//            System.out.println("true");
//        } else {
//            System.out.println("false");
//        }

        String CurrentPrice = "100";
        String SalePrice = "99";
        String[] str = SalePrice.split("\\.");
        System.out.println(str[0]);



        Integer taxPrice = Math.toIntExact(Integer.parseInt(CurrentPrice)-Integer.parseInt(str[0]));
        System.out.println(taxPrice);


    }
}

