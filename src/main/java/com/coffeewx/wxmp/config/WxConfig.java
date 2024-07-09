package com.coffeewx.wxmp.config;

import com.alibaba.fastjson.JSON;
import com.coffeewx.model.WxAccount;
import com.coffeewx.service.WxAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 初始化配置
 * @author Kevin
 * @date 2019-01-16 14:39
 */
@Component
public class WxConfig implements InitializingBean {

    private final static Logger logger = LoggerFactory.getLogger(WxConfig.class);

    @Autowired
    WxAccountService wxAccountService;

    @Override
    public void afterPropertiesSet() throws Exception {
        initWxConfig();
    }
    
    /**
     * 初始化公众号配置
     * @param 
     * @return void
     * @author Kevin
     * @date 2019-01-16 15:01:00
     */
    public void initWxConfig(){
        List<WxAccount> wxAccountList = wxAccountService.findAll();
        WxMpProperties wxMpProperties = WxMpProperties.getInstance();
        for (int i = 0; i < wxAccountList.size(); i++) {
            WxAccount wxAccount = wxAccountList.get( i );
            saveConfig( wxMpProperties, wxAccount);
        }
        WxMpConfig.init( wxMpProperties );
        logger.info( "========================================");
        logger.info( "加载公众号配置成功");
        logger.info( "========================================");
    }

    /**
     *  更新配置
     * @param wxMpProperties
     * @param wxAccount
     * @return void
     * @author Kevin
     * @date 2019-01-16 14:59:21
     */
    public static void saveConfig(WxMpProperties wxMpProperties, WxAccount wxAccount) {
        List<WxMpProperties.MpConfig> list = wxMpProperties.getConfigs();
        WxMpProperties.MpConfig mpConfig = new WxMpProperties.MpConfig();
        mpConfig.setAppId( wxAccount.getAppid() );
        mpConfig.setSecret( wxAccount.getAppsecret() );
        mpConfig.setToken(wxAccount.getToken() );
        mpConfig.setAesKey( wxAccount.getAeskey() );

        boolean isExist = false;
        for (int i = 0; i < list.size(); i++) {
            if(list.get( i ).getAppId().equals( mpConfig.getAppId() )){//update
                list.get( i ).setAesKey( mpConfig.getAesKey() );
                list.get( i ).setToken( mpConfig.getToken() );
                list.get( i ).setSecret( mpConfig.getSecret() );
                list.get( i ).setAppId( mpConfig.getAppId() );
                isExist = true;
                break;
            }else{//insert
                isExist = false;
            }
        }
        if(!isExist){
            list.add( mpConfig );
        }
    }

}
