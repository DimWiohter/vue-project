package com.coffeewx.service.impl;

import cn.hutool.core.date.DateUtil;
import com.coffeewx.core.AbstractService;
import com.coffeewx.dao.WxAccountMapper;
import com.coffeewx.model.*;
import com.coffeewx.service.*;
import com.coffeewx.wxmp.config.WxMpConfig;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2019/01/16.
 */
@Service
@Transactional
public class WxAccountServiceImpl extends AbstractService<WxAccount> implements WxAccountService {
    @Resource
    private WxAccountMapper tWxAccountMapper;

    @Autowired
    WxSubscribeTextService wxSubscribeTextService;

    @Autowired
    WxReceiveTextService wxReceiveTextService;

    @Autowired
    WxAccountFansService wxAccountFansService;

    @Autowired
    WxMenuService wxMenuService;

    @Autowired
    WxFansMsgService wxFansMsgService;

    @Autowired
    WxFansMsgResService wxFansMsgResService;

    @Autowired
    WxNewsTemplateService wxNewsTemplateService;

    @Autowired
    WxNewsArticleItemService wxNewsArticleItemService;

    @Autowired
    WxFansTagService wxFansTagService;

    @Autowired
    WxAccountFansTagService wxAccountFansTagService;

    @Override
    public List<WxAccount> findList(WxAccount wxAccount) {
        return tWxAccountMapper.findList( wxAccount );
    }

    @Override
    public void generateQRUrl(WxAccount wxAccount) throws Exception{
        wxAccount = this.findById( wxAccount.getId() );
        WxMpService wxMpService = WxMpConfig.getMpServices().get( wxAccount.getAppid() );
        WxMpQrCodeTicket ticket = wxMpService.getQrcodeService().qrCodeCreateLastTicket( 1 );
        String url = wxMpService.getQrcodeService().qrCodePictureUrl(ticket.getTicket());

        wxAccount.setQrUrl( url );
        wxAccount.setUpdateTime( DateUtil.date() );
        this.update( wxAccount );
    }

    @Override
    public void deleteRelation(String wxAccountId) throws Exception {

        //删除关联欢迎语
        WxSubscribeText wxSubscribeTextTpl = new WxSubscribeText();
        wxSubscribeTextTpl.setWxAccountId( wxAccountId );
        List<WxSubscribeText> wxSubscribeTextList = wxSubscribeTextService.findList( wxSubscribeTextTpl);
        wxSubscribeTextList.forEach( temp -> {
            wxSubscribeTextService.deleteById(temp.getId());
        });

        //删除关联回复关键字
        WxReceiveText wxReceiveTextTpl = new WxReceiveText();
        wxReceiveTextTpl.setWxAccountId( wxAccountId );
        List<WxReceiveText> wxReceiveTextList = wxReceiveTextService.findList( wxReceiveTextTpl );
        wxReceiveTextList.forEach( temp -> {
            wxReceiveTextService.deleteById( temp.getId() );
        } );

        //删除关联粉丝
        WxAccountFans wxAccountFansTpl = new WxAccountFans();
        wxAccountFansTpl.setWxAccountId( wxAccountId );
        List<WxAccountFans> wxAccountFansList = wxAccountFansService.findList( wxAccountFansTpl );
        wxAccountFansList.forEach( temp -> {
            wxAccountFansService.deleteById( temp.getId() );
        } );

        //删除关联的粉丝标签
        WxFansTag wxFansTagTpl = new WxFansTag();
        wxFansTagTpl.setWxAccountId( wxAccountId );
        List<WxFansTag> wxFansTagList = wxFansTagService.findList( wxFansTagTpl );
        wxFansTagList.forEach( temp -> {
            wxFansTagService.deleteById( temp.getId() );
        } );

        //删除关联的粉丝标签关系
        WxAccountFansTag wxAccountFansTagTpl = new WxAccountFansTag();
        wxAccountFansTagTpl.setWxAccountId( wxAccountId );
        List<WxAccountFansTag> wxAccountFansTagList = wxAccountFansTagService.findList( wxAccountFansTagTpl );
        wxAccountFansTagList.forEach( temp -> {
            wxAccountFansTagService.deleteById( temp.getId() );
        } );

        //删除关联菜单
        WxMenu wxMenuTpl = new WxMenu();
        wxMenuTpl.setWxAccountId( wxAccountId );
        List<WxMenu> wxMenuList = wxMenuService.findList( wxMenuTpl );
        wxMenuList.forEach( temp -> {
            wxMenuService.deleteById( temp.getId() );
        } );

        //删除关联粉丝消息、已回复消息
        WxFansMsg wxFansMsgTpl = new WxFansMsg();
        wxFansMsgTpl.setWxAccountId( wxAccountId );
        List<WxFansMsg> wxFansMsgList = wxFansMsgService.findList( wxFansMsgTpl );
        wxFansMsgList.forEach( temp -> {
            WxFansMsgRes wxFansMsgResTpl = new WxFansMsgRes();
            wxFansMsgResTpl.setFansMsgId( String.valueOf( temp.getId() ) );
            List<WxFansMsgRes> wxFansMsgResList = wxFansMsgResService.findList( wxFansMsgResTpl );
            wxFansMsgResList.forEach( _temp -> {
                wxFansMsgResService.deleteById( _temp.getId() );
            } );
            wxFansMsgService.deleteById( temp.getId() );
        } );


        //删除关联的图文
        WxNewsTemplate wxNewsTemplate = new WxNewsTemplate();
        wxNewsTemplate.setWxAccountId( wxAccountId );
        List<WxNewsTemplate> wxNewsTemplateList = wxNewsTemplateService.findList( wxNewsTemplate );
        wxNewsTemplateList.forEach( temp -> {
            WxNewsArticleItem wxNewsArticleItem = new WxNewsArticleItem();
            wxNewsArticleItem.setNewsId( String.valueOf( temp.getId() ) );
            List <WxNewsArticleItem> wxNewsArticleItemList = wxNewsArticleItemService.findList( wxNewsArticleItem );
            wxNewsArticleItemList.forEach( _temp -> {
                wxNewsArticleItemService.deleteById( _temp.getId() );
            } );
            wxNewsTemplateService.deleteById( temp.getId() );
        } );

        //删除账户
        this.deleteById(Integer.valueOf( wxAccountId ));
    }
}
