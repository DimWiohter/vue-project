package com.coffeewx.web;

import cn.hutool.core.date.DateUtil;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.coffeewx.model.WxAccount;
import com.coffeewx.model.WxAccountFans;
import com.coffeewx.model.vo.FansMsgVO;
import com.coffeewx.service.WxAccountFansService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2019/01/16.
*/
@RestController
@RequestMapping("/wx/account/fans")
public class WxAccountFansController extends AbstractController{
    @Resource
    private WxAccountFansService wxAccountFansService;

    @PostMapping("/add")
    public Result add(@RequestBody WxAccountFans wxAccountFans) {
        wxAccountFans.setCreateTime( DateUtil.date() );
        wxAccountFans.setUpdateTime( DateUtil.date() );
        wxAccountFansService.save(wxAccountFans);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        wxAccountFansService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody WxAccountFans wxAccountFans) {
        wxAccountFans.setUpdateTime( DateUtil.date() );
        wxAccountFansService.update(wxAccountFans);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        WxAccountFans wxAccountFans = wxAccountFansService.findById(id);
        return ResultGenerator.genSuccessResult(wxAccountFans);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer limit,@RequestParam String nicknameStr,@RequestParam String wxAccountId) {
        PageHelper.startPage(page, limit);
        PageHelper.orderBy( "subscribe_time desc" );
        WxAccountFans wxAccountFans = new WxAccountFans();
        wxAccountFans.setNicknameStr( nicknameStr );
        wxAccountFans.setWxAccountId( wxAccountId );
        List<WxAccountFans> list = wxAccountFansService.findList( wxAccountFans );
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/syncAccountFans")
    public Result syncAccountFans(@RequestBody WxAccount wxAccount) throws Exception{
        if(wxAccount.getId() == null){
            return ResultGenerator.genFailResult( "参数不全" );
        }
        wxAccountFansService.syncAccountFans( wxAccount );
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/sendMsg")
    public Result sendMsg(@RequestBody FansMsgVO fansMsgVO) throws Exception{
        wxAccountFansService.sendMsg( fansMsgVO );
        return ResultGenerator.genSuccessResult();
    }

}
