package com.coffeewx;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.coffeewx.model.vo.JiSuResVO;
import com.coffeewx.wxmp.config.JiSuAPIUtil;
import com.google.common.collect.Maps;
import com.mzlion.easyokhttp.HttpClient;

import java.util.Map;

/**
 * @author Kevin
 * @date 2019-03-21 14:20
 */
public class TestAPI {

    public static void main(String[] args) {
//        String url = "http://api.jisuapi.com/iqa/query";
//        Map<String,String> params = Maps.newHashMap();
//        params.put( "question","你是谁？" );
//        params.put( "appkey","5e520511535d885f" );
//        JiSuResVO jiSuResVO = HttpClient.post( url ).param( params).asBean(JiSuResVO.class);
//        System.out.println( JSONUtil.toJsonStr( jiSuResVO ));

        //System.out.println( new JiSuAPIUtil().getJisuapiAppkey());
    }

}
