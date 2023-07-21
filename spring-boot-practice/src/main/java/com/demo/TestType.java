package com.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author: yong.peng
 * @create: 2023/4/28 18:27
 **/

public class TestType {
    public static void main(String[] args) throws ParseException {

//        String times = "13:55";
//        String result = times.substring(0, 5); // 截取前n个字符
//        System.out.println(result);
//        System.out.println(times.length());

//        Date myDate = new Date();
//        System.out.println("myDate:" + myDate + "\n");
//
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
//        String strDate1 = sdf1.format(myDate);
//        System.out.println("strDate1:" + strDate1);
//
//        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        String strDate2 = sdf2.format(myDate);
//        System.out.println("strDate2:" + strDate2);
//
//        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String strDate3 = sdf3.format(myDate);
//        System.out.println("strDate3:" + strDate3);
//
//        SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
//        String strDate4 = sdf4.format(myDate);
//        System.out.println("strDate4:" + strDate4);

//        int minutes = 95;
//        int hours = (int)Math.floor(minutes/60.0);
//        int minute = minutes-(hours*60);
//        System.out.println(hours);
//        System.out.println(minute);
//        String totalHours = "";
//        if(hours != 0){
//            totalHours = totalHours+hours+"h";
//        }
//        if(minute != 0){
//            totalHours = totalHours+minute+"m";
//        }
//        System.out.println(totalHours);
//
//        String searchCityName = "2023-07-05";
//        String residentCity = "12:20:00";
//        String cities = String.join(" ", searchCityName, residentCity);
//        System.out.println(cities);


//        BigDecimal bMinutes = new BigDecimal(minutes);
//        BigDecimal oneMinute = new BigDecimal(60);
//        // 向下取整
//        int hours1 = (int)Math.floor(bMinutes.divide(oneMinute).doubleValue());
//        System.out.println(hours1);
//
//        Integer personAge = 18;
//        String ages = "15-22";
//
//        String[] age = ages.trim().split("-");
//
//        if (Arrays.asList(age).size() != 2){
//            System.out.println("false");
//        }
//
//        List<Integer> ageList = new ArrayList<>();
//        System.out.println("cityName:"+ Arrays.toString(age));
//        for (String s : age) {
//            ageList.add(Integer.valueOf(s));
//        }
//        if (ageList.get(0) <= personAge && ageList.get(1) >= personAge){
//            System.out.println("true");
//        }


        SimpleDateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String date = "2023-07-05 14:17:03";
        String date1 = "2023-07-26 14:16:32";
        String date2 = "2023-07-08 14:17:03";

        BlindBoxExchangeTime blindBoxExchangeTime = new BlindBoxExchangeTime();
        BlindBoxExchangeTime blindBoxExchangeTime1 = new BlindBoxExchangeTime();
        BlindBoxExchangeTime blindBoxExchangeTime2 = new BlindBoxExchangeTime();

        List<BlindBoxExchangeTime> activityTimeList = new ArrayList<>();

        List<Date> dateList = new ArrayList<>();
        Date date3 = null;

        Date date4 = null;

        Date date5 = null;

        date3 = dateFormat.parse(date);
        blindBoxExchangeTime.setStartTime(date3);
        activityTimeList.add(blindBoxExchangeTime);


        date4 = dateFormat.parse(date1);
        blindBoxExchangeTime1.setStartTime(date4);
        activityTimeList.add(blindBoxExchangeTime1);

        date5 = dateFormat.parse(date2);
        blindBoxExchangeTime2.setStartTime(date5);
        activityTimeList.add(blindBoxExchangeTime2);

        if (date3.getTime() > date4.getTime()){
            System.out.println("true");
        } else {
            System.out.println("false");
        }


//        activityTimeList.sort(new Comparator<BlindBoxExchangeTime>() {
//                                  @Override
//                                  public int compare(BlindBoxExchangeTime o1, BlindBoxExchangeTime o2) {
//                                      return o1.getStartTime().compareTo(o2.getStartTime());
//                                  }
//                              }
//
//        );

    }
}
