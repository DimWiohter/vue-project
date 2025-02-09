package com.coffeewx;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import org.springframework.util.Assert;

/**
 * TODO
 *
 * @author Kevin
 * @date 2019-02-22 11:03
 */
public class Test {

    public static void main(String[] args) {
        //Assert.isNull( "123","must null" );

        long size = HttpUtil.downloadFile("https://picsum.photos/200/300", FileUtil.file("D:/123.png"));

    }

}
