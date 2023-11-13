package com.demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author: yong.peng
 * @create: 2023/8/1 10:48
 **/

public class convertImage {
    public static void main(String[] args) {

//        ByteArrayOutputStream data = new ByteArrayOutputStream();
//        try {
////            URL url = new URL("https://dimg04.c-ctrip.com/images/0wy0912000bxb62ilF59A.png");
//            URL url = new URL("https://gimg2.baidu.com/image_search/src=https%3A%2F%2Fpics2.baidu.com%2Ffeed%2Fa686c9177f3e6709d2828ae56920c936f9dc5518.jpeg%40f_auto%3Ftoken%3Dc3428604eb884b691dbbcd734afd99a5&refer=http%3A%2F%2Fpics2.baidu.com&app=2002&size=f10000,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1694932288&t=96dd35c87e7b851e0cc5b02dfc2c9ce9");
//            byte[] by = new byte[1024];
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.setConnectTimeout(2000);
//            InputStream is = conn.getInputStream();
//            int len = -1;
//            while ((len = is.read(by)) != -1) {
//                data.write(by, 0, len);
//            }
//            is.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String encode =  Base64.getEncoder().encodeToString(data.toByteArray());
//        System.out.println(encode);

//        String departTime = "2023-08-24 10:35";
//
//        String arrivalTime = null;
//        String[] times = departTime.split(" ");
//        String[] dates = arrivalTime.split(" ");
//        String date = times[1];
//        System.out.println(date);

//        String flightNo = "yyyy-MM-dd HH:mm";
////        String[] flightNos = flightNo.split("-");
//        System.out.println(flightNo.length());

//        BigDecimal bigDecimal = new BigDecimal("150.6502021541575101");
//        System.out.println(bigDecimal.setScale(0, RoundingMode.HALF_UP).toString());
//
//        breakout:
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                System.out.println("i=" + i + ",j=" + j);
//                if (j == 5) break breakout;
//            }
//        }
//
//        int[][] arr = {{1, 2, 3}, {4, 5, 6, 7}, {9}};
//        boolean found = false;
//        for (int i = 0; i < arr.length && !found; i++) {
//            for (int j = 0; j < arr[i].length; j++) {
//                System.out.println("i = " + i + ", j = " + j);
//                if (arr[i][j] == 5) {
//                    found = true;
//                    break;
//                }
//            }
//        }
//
//        String s1 = "a";
//        String s2 = s1 + "b";
//        String s3 = "a" + "b";
//        System.out.println(s2 == "ab");
//        System.out.println(s3 == "ab");
        String s1 = "2023-10-30 12:01:12";
        System.out.println(s1.length());

    }
}
