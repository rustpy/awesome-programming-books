package com.controller;

import com.service.EatService;
import com.service.SumService;

/**
 * @author: yong.peng
 * @create: 2023/4/11 23:50
 **/

public class CatController {
    public static void main(String[] args){
        EatService e = new EatService() {
            @Override
            public void eat() {
                System.out.println("不吃");
            }
        };
        e.eat();

        EatService eat = new CatImpl();
        eat.eat();

        SumService ss = new CatImpl();
        ss.eat();
        ss.play();

    }
}
