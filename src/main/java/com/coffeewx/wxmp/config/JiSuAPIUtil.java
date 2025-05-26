package com.coffeewx.wxmp.config;

import cn.hutool.json.JSONUtil;
import com.coffeewx.model.vo.JiSuResVO;
import com.google.common.collect.Maps;
import com.mzlion.easyokhttp.HttpClient;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Kevin
 * @date 2019-03-21 15:12
 */
@Component
@Data
public class JiSuAPIUtil {

    protected Logger logger = LoggerFactory.getLogger(JiSuAPIUtil.class);

    @Value("${jisuapi.appkey}")
    private String jisuapiAppkey;

    @Value("${jisuapi.iqa.query}")
    private String jisuapiIqaQuery;

    public JiSuResVO iqaQuery(String content){
        Map<String,String> params = Maps.newHashMap();
        params.put( "question",content);
        params.put( "appkey",jisuapiAppkey );
        JiSuResVO jiSuResVO = HttpClient.post( jisuapiIqaQuery ).param( params).asBean(JiSuResVO.class);
        logger.info( "url : {}", JSONUtil.toJsonStr( jisuapiIqaQuery ));
        logger.info( "params : {}", JSONUtil.toJsonStr( params ));
        logger.info( "result : {}", JSONUtil.toJsonStr( jiSuResVO ));
        return jiSuResVO;
    }


}
