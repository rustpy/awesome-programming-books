package com.service;

/**
 * @author: yong.peng
 * @create: 2023/4/11 23:45
 **/

public interface EatService {
    default void eat(){
        System.out.println("原始猫粮");
    };
}
