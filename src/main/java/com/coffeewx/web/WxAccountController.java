package com.coffeewx.web;

import cn.hutool.core.date.DateUtil;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.coffeewx.model.*;
import com.coffeewx.service.*;
import com.coffeewx.wxmp.config.WxConfig;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2019/01/16.
*/
@RestController
@RequestMapping("/wx/account")
public class WxAccountController extends AbstractController{
    @Resource
    private WxAccountService wxAccountService;


    @Autowired
    WxSubscribeTextService wxSubscribeTextService;

    @Autowired
    WxReceiveTextService wxReceiveTextService;

    @Autowired
    WxAccountFansService wxAccountFansService;

    @Autowired
    WxMenuService wxMenuService;

    @Autowired
    WxConfig wxConfig;

    @Autowired
    WxFansMsgService wxFansMsgService;

    @Autowired
    WxFansMsgResService wxFansMsgResService;

    @PostMapping("/add")
    public Result add(@RequestBody WxAccount wxAccount) {
        wxAccount.setCreateTime( DateUtil.date() );
        wxAccount.setUpdateTime( DateUtil.date() );
        wxAccountService.save(wxAccount);
        wxConfig.initWxConfig();
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {

        //删除公众号账户，先删除欢迎语、关键字、粉丝、菜单、粉丝消息
        WxSubscribeText wxSubscribeTextTpl = new WxSubscribeText();
        wxSubscribeTextTpl.setWxAccountId( String.valueOf( id ) );
        List<WxSubscribeText> wxSubscribeTextList = wxSubscribeTextService.findList( wxSubscribeTextTpl);
        wxSubscribeTextList.forEach( temp -> {
            wxSubscribeTextService.deleteById(temp.getId());
        });

        WxReceiveText wxReceiveTextTpl = new WxReceiveText();
        wxReceiveTextTpl.setWxAccountId( String.valueOf( id ) );
        List<WxReceiveText> wxReceiveTextList = wxReceiveTextService.findList( wxReceiveTextTpl );
        wxReceiveTextList.forEach( temp -> {
            wxReceiveTextService.deleteById( temp.getId() );
        } );

        WxAccountFans wxAccountFansTpl = new WxAccountFans();
        wxAccountFansTpl.setWxAccountId( String.valueOf( id ) );
        List<WxAccountFans> wxAccountFansList = wxAccountFansService.findList( wxAccountFansTpl );
        wxAccountFansList.forEach( temp -> {
            wxAccountFansService.deleteById( temp.getId() );
        } );

        WxMenu wxMenuTpl = new WxMenu();
        wxMenuTpl.setWxAccountId( String.valueOf( id ) );
        List<WxMenu> wxMenuList = wxMenuService.findList( wxMenuTpl );
        wxMenuList.forEach( temp -> {
            wxMenuService.deleteById( temp.getId() );
        } );

        WxFansMsg wxFansMsgTpl = new WxFansMsg();
        wxFansMsgTpl.setWxAccountId( String.valueOf( id ) );
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

        wxAccountService.deleteById(id);
        wxConfig.initWxConfig();
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody WxAccount wxAccount) {
        wxAccount.setUpdateTime( DateUtil.date() );
        wxAccountService.update(wxAccount);
        wxConfig.initWxConfig();
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        WxAccount wxAccount = wxAccountService.findById(id);
        return ResultGenerator.genSuccessResult(wxAccount);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer limit,@RequestParam String name) {
        PageHelper.startPage(page, limit);
        WxAccount wxAccount = new WxAccount();
        wxAccount.setName( name );
        List<WxAccount> list = wxAccountService.findList(wxAccount);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/listAll")
    public Result listAll() {
        List<WxAccount> list = wxAccountService.findAll();
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/generateQRUrl")
    public Result generateQRUrl(@RequestBody WxAccount wxAccount) throws Exception{
        wxAccountService.generateQRUrl(wxAccount);
        return ResultGenerator.genSuccessResult();
    }

}
