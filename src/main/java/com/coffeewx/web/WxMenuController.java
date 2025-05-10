package com.coffeewx.web;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMenuService;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* Created by CodeGenerator on 2019/01/17.
*/
@RestController
@RequestMapping("/wx/menu")
public class WxMenuController {
    @Autowired
    private WxMenuService wxMenuService;

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
    public Result syncAccountMenu(@RequestBody WxAccount wxAccount) throws Exception{
        if(wxAccount.getId() == null){
            return ResultGenerator.genFailResult( "参数不全" );
        }
        wxMenuService.syncAccountMenu(wxAccount);
        return ResultGenerator.genSuccessResult();
    }

}
