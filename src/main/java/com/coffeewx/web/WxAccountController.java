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
