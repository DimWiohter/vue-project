package com.coffeewx.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.coffeewx.core.AbstractService;
import com.coffeewx.dao.WxMenuMapper;
import com.coffeewx.model.WxAccount;
import com.coffeewx.model.WxMenu;
import com.coffeewx.model.WxNewsTemplate;
import com.coffeewx.model.WxTextTemplate;
import com.coffeewx.model.vo.MenuTreeNode;
import com.coffeewx.service.WxAccountService;
import com.coffeewx.service.WxMenuService;
import com.coffeewx.service.WxNewsTemplateService;
import com.coffeewx.service.WxTextTemplateService;
import com.coffeewx.wxmp.config.WxMpConfig;
import com.google.common.collect.Lists;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.mp.api.WxMpMenuService;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by CodeGenerator on 2019/01/17.
 */
@Service
@Transactional
public class WxMenuServiceImpl extends AbstractService<WxMenu> implements WxMenuService {
    @Autowired
    private WxMenuMapper tWxMenuMapper;

    @Autowired
    private WxAccountService wxAccountService;

    @Autowired
    private WxTextTemplateService wxTextTemplateService;

    @Autowired
    private WxNewsTemplateService wxNewsTemplateService;

    @Override
    public List<WxMenu> findList(WxMenu tWxMenu){
        return tWxMenuMapper.findList(tWxMenu);
    }

    @Override
    public List<WxMenu> listTreeMenu(){
        WxMenu wxMenu = new WxMenu();
        return tWxMenuMapper.listTreeMenu(wxMenu);
    }

    @Override
    public List <WxMenu> listTreeMenu(String wxAccountId) {
        WxMenu wxMenu = new WxMenu();
        wxMenu.setWxAccountId( wxAccountId );
        return tWxMenuMapper.listTreeMenu(wxMenu);
    }

    @Override
    public List <MenuTreeNode> convertTreeMenu(List<WxMenu> list) {
        List<MenuTreeNode> menuTreeNodeList = Lists.newArrayList();

        //处理一级节点
        for (int i = 0; i < list.size(); i++) {
            WxMenu wxMenu = list.get( i );
            if(wxMenu.getMenuLevel().equals( "1" )){
                MenuTreeNode menuTreeNode = new MenuTreeNode();
                BeanUtil.copyProperties( wxMenu,menuTreeNode);
                menuTreeNodeList.add(menuTreeNode);
            }
        }

        //处理二级节点
        for (int i = 0; i < list.size(); i++) {
            WxMenu wxMenu = list.get( i );
            if(wxMenu.getMenuLevel().equals( "2" )){
                MenuTreeNode menuTreeNodeParent = findParent( wxMenu, menuTreeNodeList);
                MenuTreeNode menuTreeNode = new MenuTreeNode();
                BeanUtil.copyProperties( wxMenu,menuTreeNode);
                if(menuTreeNodeParent != null){
                    menuTreeNodeParent.getChildren().add( menuTreeNode );
                }
            }
        }
        sortMenuTreeList(menuTreeNodeList);
        return menuTreeNodeList;
    }

    @Override
    public void syncAccountMenu(WxAccount wxAccount) throws Exception {
        List<WxMenu> list = listTreeMenu(String.valueOf( wxAccount.getId() ));
        List<MenuTreeNode> menuTreeNodeList = convertTreeMenu( list );

        wxAccount = wxAccountService.findById( wxAccount.getId() );
        WxMpService wxMpService = WxMpConfig.getMpServices().get( wxAccount.getAppid() );
        WxMpMenuService wxMpMenuService = wxMpService.getMenuService();

        wxMpMenuService.menuDelete();
        me.chanjar.weixin.common.bean.menu.WxMenu wxMenu = new me.chanjar.weixin.common.bean.menu.WxMenu();

        for (int i = 0; i < menuTreeNodeList.size(); i++) {
            MenuTreeNode menuTreeNode = menuTreeNodeList.get( i );
            WxMenuButton wxMenuButton = new WxMenuButton();
            if(menuTreeNode.getMenuType().equals( "1" )){
                wxMenuButton.setType( WxConsts.MenuButtonType.CLICK);
                wxMenuButton.setName( menuTreeNode.getMenuName() );
                WxTextTemplate wxTextTemplate = wxTextTemplateService.findById( Integer.parseInt( menuTreeNode.getTplId() ) );
                wxMenuButton.setKey( wxTextTemplate.getContent() );
            }
            if(menuTreeNode.getMenuType().equals( "2" )){
                wxMenuButton.setType( WxConsts.MenuButtonType.MEDIA_ID);
                wxMenuButton.setName( menuTreeNode.getMenuName() );
                WxNewsTemplate wxNewsTemplate = wxNewsTemplateService.findById( Integer.parseInt( menuTreeNode.getTplId() ) );
                wxMenuButton.setMediaId( wxNewsTemplate.getMediaId() );
            }
            if(menuTreeNode.getMenuType().equals( "3" )){
                wxMenuButton.setType( WxConsts.MenuButtonType.VIEW);
                wxMenuButton.setName( menuTreeNode.getMenuName() );
                wxMenuButton.setUrl( menuTreeNode.getMenuUrl() );
            }
            if(menuTreeNode.getMenuType().equals( "4" )){
                wxMenuButton.setType( WxConsts.MenuButtonType.MINIPROGRAM);
                wxMenuButton.setName( menuTreeNode.getMenuName() );
                wxMenuButton.setUrl( menuTreeNode.getMenuUrl() );
                wxMenuButton.setAppId( menuTreeNode.getMiniprogramAppid() );
                wxMenuButton.setPagePath( menuTreeNode.getMiniprogramPagepath() );
            }

            if(menuTreeNode.getChildren().size() > 0){
                for (int j = 0; j < menuTreeNode.getChildren().size(); j++) {
                    MenuTreeNode menuTreeNodeSub = menuTreeNode.getChildren().get( j );
                    WxMenuButton wxMenuButtonSub = new WxMenuButton();
                    if(menuTreeNodeSub.getMenuType().equals( "1" )){
                        wxMenuButtonSub.setType( WxConsts.MenuButtonType.CLICK);
                        wxMenuButtonSub.setName( menuTreeNodeSub.getMenuName() );
                        WxTextTemplate wxTextTemplateSub = wxTextTemplateService.findById( Integer.parseInt( menuTreeNodeSub.getTplId() ) );
                        wxMenuButtonSub.setKey( wxTextTemplateSub.getContent() );
                    }
                    if(menuTreeNodeSub.getMenuType().equals( "2" )){
                        wxMenuButtonSub.setType( WxConsts.MenuButtonType.MEDIA_ID);
                        wxMenuButtonSub.setName( menuTreeNodeSub.getMenuName() );
                        WxNewsTemplate wxNewsTemplate = wxNewsTemplateService.findById( Integer.parseInt( menuTreeNodeSub.getTplId() ) );
                        wxMenuButtonSub.setMediaId( wxNewsTemplate.getMediaId() );
                    }
                    if(menuTreeNodeSub.getMenuType().equals( "3" )){
                        wxMenuButtonSub.setType( WxConsts.MenuButtonType.VIEW);
                        wxMenuButtonSub.setName( menuTreeNodeSub.getMenuName() );
                        wxMenuButtonSub.setUrl( menuTreeNodeSub.getMenuUrl() );
                    }
                    if(menuTreeNode.getMenuType().equals( "4" )){
                        wxMenuButtonSub.setType( WxConsts.MenuButtonType.MINIPROGRAM);
                        wxMenuButtonSub.setName( menuTreeNode.getMenuName() );
                        wxMenuButtonSub.setUrl( menuTreeNode.getMenuUrl() );
                        wxMenuButtonSub.setAppId( menuTreeNode.getMiniprogramAppid() );
                        wxMenuButtonSub.setPagePath( menuTreeNode.getMiniprogramPagepath() );
                    }
                    wxMenuButton.getSubButtons().add( wxMenuButtonSub );
                }
            }
            wxMenu.getButtons().add(wxMenuButton);
        }

        wxMpMenuService.menuCreate( wxMenu );

    }

    MenuTreeNode findParent(WxMenu wxMenu,List<MenuTreeNode> menuTreeNodeList){
        for (int i = 0; i < menuTreeNodeList.size(); i++) {
            if(menuTreeNodeList.get( i ).getId().equals( Integer.parseInt( wxMenu.getParentId() ) )){
                return menuTreeNodeList.get( i );
            }
        }
        return null;
    }

    void sortMenuTreeList(List<MenuTreeNode> menuTreeNodeList){
        sortList(menuTreeNodeList);
        for (int i = 0; i < menuTreeNodeList.size(); i++) {
            if(menuTreeNodeList.get( i ).getChildren().size() > 0){
                sortMenuTreeList(menuTreeNodeList.get( i ).getChildren());
            }
        }
    }

    void sortList(List<MenuTreeNode> menuTreeNodeList){
        Collections.sort(menuTreeNodeList, Comparator.comparing(MenuTreeNode::getWxAccountId).thenComparing(MenuTreeNode::getMenuSort));
    }

}
