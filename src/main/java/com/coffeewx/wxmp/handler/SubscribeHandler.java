package com.coffeewx.wxmp.handler;

import com.alibaba.fastjson.JSON;
import com.coffeewx.model.WxAccount;
import com.coffeewx.model.WxReceiveText;
import com.coffeewx.model.WxSubscribeText;
import com.coffeewx.model.WxTextTemplate;
import com.coffeewx.service.WxAccountService;
import com.coffeewx.service.WxReceiveTextService;
import com.coffeewx.service.WxSubscribeTextService;
import com.coffeewx.service.WxTextTemplateService;
import com.coffeewx.wxmp.builder.TextBuilder;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class SubscribeHandler extends AbstractHandler {

    @Autowired
    WxReceiveTextService wxReceiveTextService;

    @Autowired
    WxTextTemplateService wxTextTemplateService;

    @Autowired
    WxAccountService wxAccountService;

    @Autowired
    WxSubscribeTextService wxSubscribeTextService;


    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) throws WxErrorException {

        this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());

        // 获取微信用户基本信息
        try {
            WxMpUser userWxInfo = weixinService.getUserService()
                .userInfo(wxMessage.getFromUser(), null);
            if (userWxInfo != null) {
                // TODO 可以添加关注用户到本地数据库
            }
        } catch (WxErrorException e) {
            if (e.getError().getErrorCode() == 48001) {
                this.logger.info("该公众号没有获取用户信息权限！");
            }
        }


        WxMpXmlOutMessage responseResult = null;
        try {
            responseResult = this.handleSpecial(wxMessage);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        if (responseResult != null) {
            return responseResult;
        }

        try {
            String content = "感谢关注！";//默认
            WxAccount wxAccount = wxAccountService.findBy( "account",wxMessage.getToUser());
            if(wxAccount != null){
                WxSubscribeText wxSubscribeText = wxSubscribeTextService.findBy( "wxAccountId", String.valueOf( wxAccount.getId() ));
                if(wxSubscribeText != null){
                    content = wxSubscribeText.getTplContent();
                }
            }
            logger.info( "wxMessage : {}", JSON.toJSONString(wxMessage) );
            return new TextBuilder().build(content, wxMessage, weixinService);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
     */
    private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage)
        throws Exception {
        //TODO
        return null;
    }

}
