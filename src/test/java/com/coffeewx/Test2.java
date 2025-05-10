package com.coffeewx;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.coffeewx.utils.URLUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * TODO
 *
 * @author Kevin
 * @date 2019-03-20 12:14
 */
public class Test2 {

    public static void main(String[] args) {

        String content = "<p><img class=\"wscnph\" src=\"http://localhost:9999/api/showImage?filepath=c:/web-folder/images/news/1553054949821_coffee.png\" width=\"200\" height=\"200\" /><img class=\"wscnph\" src=\"http://localhost:9999/api/showImage?filepath=c:/web-folder/images/news/1553054965402_微信图片_20190222133245.png\" width=\"100\" height=\"100\" /><img src=\"https://picsum.photos/200/300\" alt=\"\" width=\"200\" height=\"300\" /></p>\n" +
                "<p>测试</p>\n" +
                "<p>测试</p>\n" +
                "<p>测试</p>\n" +
                "<p>测试</p>\n" +
                "<p>测试</p>\n" +
                "<p>测试</p>";

        String imgReg = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
        List<String> list2 = ReUtil.findAllGroup1( imgReg,content);

        for (int i = 0; i < list2.size(); i++) {
            String filepath = URLUtils.getParam( list2.get( i ),"filepath" );
            if(StringUtils.isNotBlank( filepath )){
                content = StringUtils.replace( content,list2.get( i ) ,"1231" );
            }else{
                //网络图片URL，需下载到本地
                //long size = HttpUtil.downloadFile("https://picsum.photos/200/300", FileUtil.file("D:/123.png"));
            }
            System.out.println(content);
        }

    }

}
