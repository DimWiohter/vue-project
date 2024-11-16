package com.coffeewx.web;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.coffeewx.model.WxAccount;
import com.coffeewx.model.WxAccountFans;
import com.coffeewx.model.WxMenu;
import com.coffeewx.model.WxTextTemplate;
import com.coffeewx.model.vo.MenuTreeNode;
import com.coffeewx.service.WxAccountService;
import com.coffeewx.service.WxMenuService;
import com.coffeewx.service.WxTextTemplateService;
import com.coffeewx.wxmp.config.WxMpConfig;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMenuService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpUserService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
* Created by CodeGenerator on 2019/01/17.
*/
@RestController
@RequestMapping("/wx/menu")
public class WxMenuController {
    @Autowired
    private WxMenuService wxMenuService;

    @Autowired
    private WxAccountService wxAccountService;

    @Autowired
    private WxTextTemplateService wxTextTemplateService;

    @PostMapping("/add")
    public Result add(@RequestBody WxMenu wxMenu) {
        wxMenu.setCreateTime( DateUtil.date() );
        wxMenu.setUpdateTime( DateUtil.date() );
        wxMenuService.save(wxMenu);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        WxMenu wxMenu = wxMenuService.findById( id );
        if(wxMenu.getMenuLevel().equals( "1" )){
            WxMenu wxMenuTpl = new WxMenu();
            wxMenuTpl.setParentId( String.valueOf( id ) );
            List<WxMenu> list = wxMenuService.findList( wxMenuTpl );
            for (int i = 0; i < list.size(); i++) {
                wxMenuService.deleteById(list.get( i ).getId());
            }
        }
        wxMenuService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody WxMenu wxMenu) {
        wxMenu.setUpdateTime( DateUtil.date() );
        wxMenuService.update(wxMenu);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        WxMenu wxMenu = wxMenuService.findById(id);
        return ResultGenerator.genSuccessResult(wxMenu);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer limit) {
        PageHelper.startPage(page, limit);
        List<WxMenu> list = wxMenuService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/listTreeMenu")
    public Result listTreeMenu() {
        List<WxMenu> list = wxMenuService.listTreeMenu();
        List<MenuTreeNode> menuTreeNodeList = wxMenuService.convertTreeMenu( list );
        return ResultGenerator.genSuccessResult(menuTreeNodeList);
    }

    @PostMapping("/validateData")
    public Result validateData(@RequestBody WxMenu wxMenu) {
        JSONObject json = new JSONObject(  );
        if(wxMenu.getMenuLevel().equals( "1" )){
            WxMenu wxMenuTpl = new WxMenu();
            wxMenuTpl.setWxAccountId( wxMenu.getWxAccountId() );
            wxMenuTpl.setMenuLevel( wxMenu.getMenuLevel() );
            List<WxMenu> list = wxMenuService.findList( wxMenuTpl );
            if(list.size() >= 3){
                json.put( "isCanCreate", false);
                json.put( "msg", "每个公众号最多创建三个菜单" );
            }else{
                json.put( "isCanCreate", true);
                json.put( "msg", "" );
            }
        }
        if(wxMenu.getMenuLevel().equals( "2" )){
            WxMenu wxMenuTpl = new WxMenu();
            wxMenuTpl.setWxAccountId( wxMenu.getWxAccountId() );
            wxMenuTpl.setParentId( wxMenu.getParentId() );
            wxMenuTpl.setMenuLevel( wxMenu.getMenuLevel() );
            List<WxMenu> list = wxMenuService.findList( wxMenuTpl );
            if(list.size() >= 5){
                json.put( "isCanCreate", false);
                json.put( "msg", "二级菜单最多创建五个" );
            }else{
                json.put( "isCanCreate", true);
                json.put( "msg", "" );
            }
        }
        return ResultGenerator.genSuccessResult(json);
    }

    @PostMapping("/syncAccountMenu")
    public Result syncAccountMenu(@RequestBody WxAccount wxAccount) {
        if(wxAccount.getId() == null){
            return ResultGenerator.genFailResult( "参数不全" );
        }

        List<WxMenu> list = wxMenuService.listTreeMenu(String.valueOf( wxAccount.getId() ));
        List<MenuTreeNode> menuTreeNodeList = wxMenuService.convertTreeMenu( list );

        wxAccount = wxAccountService.findById( wxAccount.getId() );
        WxMpService wxMpService = WxMpConfig.getMpServices().get( wxAccount.getAppid() );
        WxMpMenuService wxMpMenuService = wxMpService.getMenuService();
        try {
            wxMpMenuService.menuDelete();
            me.chanjar.weixin.common.bean.menu.WxMenu wxMenu = new me.chanjar.weixin.common.bean.menu.WxMenu();

            for (int i = 0; i < menuTreeNodeList.size(); i++) {
                MenuTreeNode menuTreeNode = menuTreeNodeList.get( i );
                WxMenuButton wxMenuButton = new WxMenuButton();
                if(menuTreeNode.getMenuType().equals( "1" ) || menuTreeNode.getMenuType().equals( "2" )){
                    wxMenuButton.setType( WxConsts.MenuButtonType.CLICK);
                    wxMenuButton.setName( menuTreeNode.getMenuName() );
                    WxTextTemplate wxTextTemplate = wxTextTemplateService.findById( Integer.parseInt( menuTreeNode.getTplId() ) );
                    wxMenuButton.setKey( wxTextTemplate.getContent() );
                }
                if(menuTreeNode.getMenuType().equals( "3" )){
                    wxMenuButton.setType( WxConsts.MenuButtonType.VIEW);
                    wxMenuButton.setName( menuTreeNode.getMenuName() );
                    wxMenuButton.setUrl( menuTreeNode.getMenuUrl() );
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
                            wxMenuButtonSub.setType( WxConsts.MenuButtonType.CLICK);
                            wxMenuButtonSub.setName( menuTreeNodeSub.getMenuName() );
                            WxTextTemplate wxTextTemplateSub = wxTextTemplateService.findById( Integer.parseInt( menuTreeNodeSub.getTplId() ) );
                            wxMenuButtonSub.setKey( wxTextTemplateSub.getContent() );
                        }
                        if(menuTreeNodeSub.getMenuType().equals( "3" )){
                            wxMenuButtonSub.setType( WxConsts.MenuButtonType.VIEW);
                            wxMenuButtonSub.setName( menuTreeNodeSub.getMenuName() );
                            wxMenuButtonSub.setUrl( menuTreeNodeSub.getMenuUrl() );
                        }
                        wxMenuButton.getSubButtons().add( wxMenuButtonSub );
                    }
                }
                wxMenu.getButtons().add(wxMenuButton);
            }

            wxMpMenuService.menuCreate( wxMenu );

        } catch (WxErrorException e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult( e.getMessage() );
        }

        return ResultGenerator.genSuccessResult();
    }

}
