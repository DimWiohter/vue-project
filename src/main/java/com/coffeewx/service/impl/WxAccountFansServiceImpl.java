package com.coffeewx.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.coffeewx.core.AbstractService;
import com.coffeewx.dao.WxAccountFansMapper;
import com.coffeewx.model.WxAccount;
import com.coffeewx.model.WxAccountFans;
import com.coffeewx.model.WxNewsTemplate;
import com.coffeewx.model.vo.FansMsgVO;
import com.coffeewx.service.WxAccountFansService;
import com.coffeewx.service.WxAccountFansTagService;
import com.coffeewx.service.WxAccountService;
import com.coffeewx.service.WxNewsTemplateService;
import com.coffeewx.wxmp.config.WxMpConfig;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpKefuService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpUserService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;


/**
 * Created by CodeGenerator on 2019/01/16.
 */
@Service
@Transactional
public class WxAccountFansServiceImpl extends AbstractService<WxAccountFans> implements WxAccountFansService {
    @Resource
    private WxAccountFansMapper tWxAccountFansMapper;


    @Autowired
    private WxAccountService wxAccountService;

    @Autowired
    private WxNewsTemplateService wxNewsTemplateService;

    @Autowired
    WxAccountFansTagService wxAccountFansTagService;

    @Override
    public List<WxAccountFans> findList(WxAccountFans wxAccountFans) {
        return tWxAccountFansMapper.findList( wxAccountFans );
    }

    @Override
    public void syncAccountFans(WxAccount wxAccount) throws Exception {
        wxAccount = wxAccountService.findById( wxAccount.getId() );
        WxMpService wxMpService = WxMpConfig.getMpServices().get( wxAccount.getAppid() );
        WxMpUserService wxMpUserService = wxMpService.getUserService();

        String nextOpenid = null;
        WxMpUserList wxMpUserList = wxMpUserService.userList( nextOpenid );
        List<WxMpUser> wxMpUsers = wxMpUserService.userInfoList( wxMpUserList.getOpenids() );
        for (int i = 0; i < wxMpUsers.size(); i++) {
            WxMpUser wxmpUser = wxMpUsers.get( i );
            logger.info( "wxMpUsers : {}", JSON.toJSONString(wxmpUser) );
            WxAccountFans wxAccountFans = this.findBy( "openid", wxmpUser.getOpenId() );
            if(wxAccountFans == null){//insert
                wxAccountFans = new WxAccountFans();
                wxAccountFans.setOpenid(wxmpUser.getOpenId());
                wxAccountFans.setSubscribeStatus(wxmpUser.getSubscribe() ? "1" : "0");
                wxAccountFans.setSubscribeTime( DateUtil.date(wxmpUser.getSubscribeTime() * 1000L ));
                try {
                    wxAccountFans.setNickname(wxmpUser.getNickname().getBytes("UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                wxAccountFans.setGender(String.valueOf( wxmpUser.getSex() ));
                wxAccountFans.setLanguage(wxmpUser.getLanguage());
                wxAccountFans.setCountry(wxmpUser.getCountry());
                wxAccountFans.setProvince(wxmpUser.getProvince());
                wxAccountFans.setCity(wxmpUser.getCity());
                wxAccountFans.setHeadimgUrl(wxmpUser.getHeadImgUrl());
                wxAccountFans.setRemark(wxmpUser.getRemark());
                wxAccountFans.setWxAccountId( String.valueOf( wxAccount.getId() ) );
                wxAccountFans.setWxAccountAppid( wxAccount.getAppid() );
                wxAccountFans.setCreateTime( DateUtil.date() );
                wxAccountFans.setUpdateTime( DateUtil.date() );
                this.save( wxAccountFans );

                //process tags
                wxAccountFansTagService.processFansTags(wxAccount,wxmpUser);

            }else{//update
                wxAccountFans.setOpenid(wxmpUser.getOpenId());
                wxAccountFans.setSubscribeStatus(wxmpUser.getSubscribe() ? "1" : "0");
                wxAccountFans.setSubscribeTime(DateUtil.date(wxmpUser.getSubscribeTime() * 1000L ));
                try {
                    wxAccountFans.setNickname(wxmpUser.getNickname().getBytes("UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                wxAccountFans.setGender(String.valueOf( wxmpUser.getSex() ));
                wxAccountFans.setLanguage(wxmpUser.getLanguage());
                wxAccountFans.setCountry(wxmpUser.getCountry());
                wxAccountFans.setProvince(wxmpUser.getProvince());
                wxAccountFans.setCity(wxmpUser.getCity());
                wxAccountFans.setHeadimgUrl(wxmpUser.getHeadImgUrl());
                wxAccountFans.setRemark(wxmpUser.getRemark());
                wxAccountFans.setWxAccountId( String.valueOf( wxAccount.getId() ) );
                wxAccountFans.setWxAccountAppid( wxAccount.getAppid() );
                wxAccountFans.setUpdateTime( DateUtil.date() );
                this.update( wxAccountFans );

                //process tags
                wxAccountFansTagService.processFansTags(wxAccount,wxmpUser);

            }
        }
    }

    @Override
    public void sendMsg(FansMsgVO fansMsgVO) throws Exception {
        WxAccountFans wxAccountFans = this.findById( Integer.valueOf( fansMsgVO.getFansId() ) );
        WxAccount wxAccount = wxAccountService.findById( Integer.parseInt( wxAccountFans.getWxAccountId() ) );
        WxMpService wxMpService = WxMpConfig.getMpServices().get( wxAccount.getAppid() );
        WxMpKefuService wxMpKefuService = wxMpService.getKefuService();
        WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
        wxMpKefuMessage.setToUser(wxAccountFans.getOpenid());
        if(fansMsgVO.getMsgType().equals( WxConsts.KefuMsgType.TEXT )){
            wxMpKefuMessage.setMsgType( WxConsts.KefuMsgType.TEXT);
            wxMpKefuMessage.setContent(fansMsgVO.getContent());
            wxMpKefuService.sendKefuMessage( wxMpKefuMessage );
        }
        if(fansMsgVO.getMsgType().equals( WxConsts.KefuMsgType.MPNEWS )){
            wxMpKefuMessage.setMsgType( WxConsts.KefuMsgType.MPNEWS);
            WxNewsTemplate wxNewsTemplate = wxNewsTemplateService.findById( Integer.parseInt( fansMsgVO.getTplId() ) );
            wxMpKefuMessage.setMpNewsMediaId( wxNewsTemplate.getMediaId() );
            wxMpKefuService.sendKefuMessage( wxMpKefuMessage );
        }
    }

}
