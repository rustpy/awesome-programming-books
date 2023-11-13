package com.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author: yong.peng
 * @create: 2023/8/1 10:48
 **/

public class DateUtil {
    public static void main(String[] args) {
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String currDate = format.format(d);
        System.out.println("现在的日期是：" + currDate);

        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, 3);// num为增加的天数，可以改变的
        d = ca.getTime();
            String endDate = format.format(d);
        System.out.println("增加天数以后的日期：" + endDate);

        String createDate = "2000-01-01";
        System.out.println("指定的日期为："+createDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int day = 5;
        try {
            Date date = sdf.parse(createDate);
            Calendar cl = Calendar.getInstance();
            cl.setTime(date);
            cl.add(Calendar.DATE, day);
            String temp = "";
            temp = sdf.format(cl.getTime());
            System.out.println("加上天数的日期："+temp);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        List<String> list = new ArrayList<>();
        list.add(null);
        System.out.println(list.size());

        String str = "2023-07-05 12:20:00";
        String [] date = str.split(" ");
        String date1 = date[0];
        String time1 = date[1].substring(0, 5);
        System.out.println(date1);
        System.out.println(time1);

        String airlineCode = "CA/MU";
        String [] airlineCodes = airlineCode.split("/");
        System.out.println(airlineCodes[0]);

        String languageType = "zh_CN_xx";
        System.out.println("语言为：" + languageType.toLowerCase());

    }
}
