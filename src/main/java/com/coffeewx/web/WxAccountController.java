package com.coffeewx.web;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.coffeewx.common.jwt.JwtUser;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.coffeewx.model.*;
import com.coffeewx.model.vo.UserTreeNode;
import com.coffeewx.model.vo.WxAccountTreeNode;
import com.coffeewx.service.*;
import com.coffeewx.utils.UserUtils;
import com.coffeewx.wxmp.config.WxConfig;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    WxConfig wxConfig;

    @PostMapping("/add")
    public Result add(@RequestBody WxAccount wxAccount) {
        wxAccount.setCreateTime( DateUtil.date() );
        wxAccount.setUpdateTime( DateUtil.date() );
        wxAccountService.save(wxAccount);
        wxConfig.initWxConfig();
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) throws Exception{
        wxAccountService.deleteRelation(String.valueOf( id ));
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

        //注意位置，和PageHelper有关，在分页之前执行
        String wxAccountIds = UserUtils.getWxAccountIds();

        PageHelper.startPage(page, limit);
        WxAccount wxAccount = new WxAccount();
        wxAccount.setName( name );

        //权限过滤
        wxAccount.setFilterRole( true );
        wxAccount.setWxAccountIds( wxAccountIds );

        List<WxAccount> list = wxAccountService.findList(wxAccount);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/listAll")
    public Result listAll() {
        //注意位置，和PageHelper有关，在分页之前执行
        String wxAccountIds = UserUtils.getWxAccountIds();
        WxAccount wxAccount = new WxAccount();
        //权限过滤
        wxAccount.setFilterRole( true );
        wxAccount.setWxAccountIds( wxAccountIds );
        List<WxAccount> list = wxAccountService.findList(wxAccount);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/generateQRUrl")
    public Result generateQRUrl(@RequestBody WxAccount wxAccount) throws Exception{
        wxAccountService.generateQRUrl(wxAccount);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/listTreeWxAccount")
    public Result listTreeWxAccount() {
        WxAccount wxAccount = new WxAccount();
        List<WxAccount> list = wxAccountService.findList(wxAccount);
        List<WxAccountTreeNode> wxAccountTreeNodeList = Lists.newArrayList();
        list.forEach( temp -> {
            WxAccountTreeNode wxAccountTreeNode = new WxAccountTreeNode();
            BeanUtil.copyProperties( temp,wxAccountTreeNode );
            wxAccountTreeNodeList.add( wxAccountTreeNode );
        } );
        JSONObject json = new JSONObject(  );
        json.put( "wxAccountList", list);
        json.put( "wxAccountTree", wxAccountTreeNodeList);
        return ResultGenerator.genSuccessResult(json);
    }

}
