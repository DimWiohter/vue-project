package com.coffeewx.test;

import cn.hutool.crypto.SecureUtil;

/**
 * 测试数据
 * @author Kevin
 * @date 2019-01-10 17:05
 */
public class TestData2 {

    public static void main(String[] args) {
        System.out.println( SecureUtil.md5( "123456" ));
    }

}
