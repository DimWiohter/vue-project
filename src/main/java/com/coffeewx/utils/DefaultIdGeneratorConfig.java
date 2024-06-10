package com.coffeewx.utils;

/**
 * @Description:IdGenerator默认配置
 * @Author:Kevin
 * @Date:2018-11-01 20:45
 */
public class DefaultIdGeneratorConfig implements IdGeneratorConfig{

    @Override
    public String getSplitString() {
        return "";
    }

    @Override
    public int getInitial() {
        return 1;
    }

    @Override
    public String getPrefix() {
        return "";
    }

    @Override
    public int getRollingInterval() {
        return 1;
    }
}
