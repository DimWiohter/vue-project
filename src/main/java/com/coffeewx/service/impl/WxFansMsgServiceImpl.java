package com.coffeewx.service.impl;

import cn.hutool.core.date.DateUtil;
import com.coffeewx.core.AbstractService;
import com.coffeewx.dao.WxFansMsgMapper;
import com.coffeewx.model.User;
import com.coffeewx.model.WxAccount;
import com.coffeewx.model.WxFansMsg;
import com.coffeewx.model.WxFansMsgRes;
import com.coffeewx.service.UserService;
import com.coffeewx.service.WxAccountService;
import com.coffeewx.service.WxFansMsgResService;
import com.coffeewx.service.WxFansMsgService;
import com.coffeewx.utils.BaseContextHandler;
import com.coffeewx.wxmp.config.WxMpConfig;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpKefuService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by CodeGenerator on 2019/03/13.
 */
@Service
@Transactional
public class WxFansMsgServiceImpl extends AbstractService<WxFansMsg> implements WxFansMsgService {
    @Autowired
    private WxFansMsgMapper tWxFansMsgMapper;

    @Autowired
    private WxFansMsgResService wxFansMsgResService;

    @Autowired
    private UserService userService;

    @Autowired
    private WxAccountService wxAccountService;

    @Override
    public List<WxFansMsg> findList(WxFansMsg tWxFansMsg){
        return tWxFansMsgMapper.findList(tWxFansMsg);
    }

    @Override
    public void updateResContent(WxFansMsg wxFansMsg) throws Exception {

        WxFansMsgRes wxFansMsgRes = new WxFansMsgRes();
        wxFansMsgRes.setResContent( wxFansMsg.getResContent() );
        wxFansMsgRes.setFansMsgId( String.valueOf( wxFansMsg.getId() ) );
        if(StringUtils.isNotBlank( BaseContextHandler.getUserID() )){
            User user = userService.findById( Integer.parseInt( BaseContextHandler.getUserID() ) );
            wxFansMsgRes.setUserId( BaseContextHandler.getUserID() );
            wxFansMsgRes.setUserName( user.getUsername() );
        }
        wxFansMsgRes.setCreateTime( DateUtil.date() );
        wxFansMsgRes.setUpdateTime( DateUtil.date() );
        wxFansMsgResService.save( wxFansMsgRes );


        wxFansMsg.setUpdateTime( DateUtil.date() );
        this.update(wxFansMsg);

        WxFansMsg wxFansMsg2 = this.findById( wxFansMsg.getId() );
        WxAccount wxAccount = wxAccountService.findById( Integer.parseInt( wxFansMsg2.getWxAccountId() ) );
        WxMpService wxMpService = WxMpConfig.getMpServices().get( wxAccount.getAppid() );
        WxMpKefuService wxMpKefuService = wxMpService.getKefuService();
        WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
        wxMpKefuMessage.setToUser(wxFansMsg2.getOpenid());
        wxMpKefuMessage.setMsgType( WxConsts.KefuMsgType.TEXT);
        wxMpKefuMessage.setContent(wxFansMsg2.getResContent());
        wxMpKefuService.sendKefuMessage( wxMpKefuMessage );
    }

}
