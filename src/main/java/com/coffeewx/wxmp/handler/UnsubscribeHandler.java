package com.coffeewx.wxmp.handler;

import cn.hutool.core.date.DateUtil;
import com.coffeewx.model.WxAccount;
import com.coffeewx.model.WxAccountFans;
import com.coffeewx.service.WxAccountFansService;
import com.coffeewx.service.WxAccountService;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class UnsubscribeHandler extends AbstractHandler {

    @Autowired
    WxAccountService wxAccountService;

    @Autowired
    WxAccountFansService wxAccountFansService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
        String openId = wxMessage.getFromUser();
        this.logger.info("取消关注用户 OPENID: " + openId);
        // TODO 可以更新本地数据库为取消关注状态

        WxAccount wxAccount = wxAccountService.findBy( "account",wxMessage.getToUser());
        if(wxAccount != null){
            WxAccountFans wxAccountFans = wxAccountFansService.findBy( "openid", openId);
            if(wxAccountFans == null){//insert
                //暂不做处理
            }else{//update
                wxAccountFans.setSubscribeStatus("0");//取消订阅
                wxAccountFans.setUpdateTime( DateUtil.date() );
                wxAccountFansService.update( wxAccountFans );
            }
        }


        return null;
    }

}
