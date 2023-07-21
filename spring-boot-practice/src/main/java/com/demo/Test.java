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

        List<String> request1 = Arrays.asList("谷歌", "腾讯", "百度", "淘宝");
        for (String str : request1) {
            System.out.println(str);
        }

        String targetCity = "SHA,BJS";
        String gpsCity = "AAA";
        Boolean test = targetCity.contains(gpsCity);
        if(targetCity.contains(gpsCity)){
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        String CurrentPrice = "100";
        String SalePrice = "99";
        String[] str = SalePrice.split("\\.");
        System.out.println(str[0]);
        Integer taxPrice = Math.toIntExact(Integer.parseInt(CurrentPrice)-Integer.parseInt(str[0]));
        System.out.println(taxPrice);

        String citys;
        String multi_resident_citycode = "SHA,WUH";
        String month_search_citycode = "";

        citys = String.join(",", multi_resident_citycode,month_search_citycode);
        if(!StringUtils.isEmpty(citys)){
            System.out.println("citys:"+citys);
        } else {
            System.out.println("citys:"+citys);
        }

        String[] city = citys.trim().split(",");
        for (String s : city) {
            System.out.println("cityName:"+s);
            }
        }

        LocalTime time = LocalTime.now();
        String times = "13:55:00";
        String result = times.substring(0, 4); // 截取前n个字符

}

