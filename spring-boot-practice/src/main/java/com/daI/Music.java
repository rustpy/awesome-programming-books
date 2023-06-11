package com.daI;


import java.util.ArrayList;
import java.util.List;

/**
 * @author: yong.peng
 * @create: 2023/4/19 20:25
 **/


public class Music {
    public static void main(String[] args) {
        List<Instrument> instruments = new ArrayList<>();
        instruments.add(new Wind());

        Integer a = 100;
        Integer b = 100;
        Integer c = 128;
        Integer d = 128;
        int e = 100;
        int f = 100;
        System.out.println(a==b);
        System.out.println(a.equals(b));
        System.out.println(c==d);
        System.out.println(e==f);



        for(Instrument instrument : instruments) {
            instrument.play();
        }
    }
}