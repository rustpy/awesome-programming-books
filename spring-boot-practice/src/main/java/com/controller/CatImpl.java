package com.controller;

import com.service.SumService;

/**
 * @author: yong.peng
 * @create: 2023/4/11 23:49
 **/

public class CatImpl implements SumService {
    @Override
    public void eat(){
        System.out.println("猫吃鱼");
    }

    @Override
    public void play() {
        System.out.println("猫玩猫抓板");
    }
}
