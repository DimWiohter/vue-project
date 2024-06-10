package com.coffeewx.utils;

/**
 * @Description:ID生成器接口, 用于生成全局唯一的ID流水号
 * @Author:Kevin
 * @Date:2018-11-01 20:42
 */
public interface IdGenerator {

    /**
    * @Description: 生成下一个不重复的流水号
    * @Param: [] 
    * @Return: java.lang.String 
    * @Author: Kevin
    * @Date: 2018/11/1 
    */
    String next();

}
