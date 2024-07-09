package com.coffeewx.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.coffeewx.dao.WxMenuMapper;
import com.coffeewx.model.WxMenu;
import com.coffeewx.model.vo.MenuTreeNode;
import com.coffeewx.service.WxMenuService;
import com.coffeewx.core.AbstractService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

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
