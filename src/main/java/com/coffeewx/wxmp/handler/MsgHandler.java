package com.coffeewx.wxmp.handler;

import com.alibaba.fastjson.JSON;
import com.coffeewx.model.WxAccount;
import com.coffeewx.model.WxReceiveText;
import com.coffeewx.model.WxTextTemplate;
import com.coffeewx.service.WxAccountService;
import com.coffeewx.service.WxReceiveTextService;
import com.coffeewx.service.WxTextTemplateService;
import com.coffeewx.wxmp.builder.TextBuilder;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class MsgHandler extends AbstractHandler {


    @Autowired
    WxReceiveTextService wxReceiveTextService;

    @Autowired
    WxTextTemplateService wxTextTemplateService;

    @Autowired
    WxAccountService wxAccountService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            //TODO 可以选择将消息保存到本地
        }

        //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
//        try {
//            if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
//                && weixinService.getKefuService().kfOnlineList()
//                .getKfOnlineList().size() > 0) {
//                return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
//                    .fromUser(wxMessage.getToUser())
//                    .toUser(wxMessage.getFromUser()).build();
//            }
//        } catch (WxErrorException e) {
//            e.printStackTrace();
//        }

        //TODO 组装回复消息
        String content = "您好，有什么问题？";//默认
        logger.info( "收到信息内容:｛｝", JSON.toJSONString(wxMessage) );
        logger.info( "关键字:｛｝", wxMessage.getContent() );

        WxAccount wxAccount = wxAccountService.findBy( "account",wxMessage.getToUser());
        if(wxAccount != null){
            WxReceiveText wxReceiveTextTpl = new WxReceiveText();
            wxReceiveTextTpl.setWxAccountId( String.valueOf( wxAccount.getId() ) );
            wxReceiveTextTpl.setReceiveText( wxMessage.getContent() );
            List<WxReceiveText> wxReceiveTextList = wxReceiveTextService.findListByReceiveTest( wxReceiveTextTpl );
            if(wxReceiveTextList != null && wxReceiveTextList.size() > 0){
                WxReceiveText wxReceiveText = wxReceiveTextList.get( 0 );
                WxTextTemplate wxTextTemplate = wxTextTemplateService.findById( Integer.parseInt( wxReceiveText.getTplId() ) );
                content = wxTextTemplate.getContent();
            }
        }
        return new TextBuilder().build(content, wxMessage, weixinService);

    }

}
