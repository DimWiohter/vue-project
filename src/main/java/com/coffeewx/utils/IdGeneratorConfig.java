package com.coffeewx.utils;

/**
 * @Description:ID生成器的配置接口
 * @Author:Kevin
 * @Date:2018-11-01 20:42
 */
public interface IdGeneratorConfig {

    /**
    * @Description: 获取分隔符
    * @Param: []
    * @Return: java.lang.String
    * @Author: Kevin
    * @Date: 2018/11/1
    */
    String getSplitString();

    /**
    * @Description: 获取初始值
    * @Param: []
    * @Return: int
    * @Author: Kevin
    * @Date: 2018/11/1
    */
    int getInitial();

    /**
    * @Description: 获取ID前缀
    * @Param: []
    * @Return: java.lang.String
    * @Author: Kevin
    * @Date: 2018/11/1
    */
    String getPrefix();

    /**
    * @Description: 获取滚动间隔, 单位: 秒
    * @Param: []
    * @Return: int
    * @Author: Kevin
    * @Date: 2018/11/1
    */
    int getRollingInterval();

}
