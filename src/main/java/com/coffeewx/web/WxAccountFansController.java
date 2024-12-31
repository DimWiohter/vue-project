package com.coffeewx.web;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.coffeewx.model.WxAccount;
import com.coffeewx.model.WxAccountFans;
import com.coffeewx.service.WxAccountFansService;
import com.coffeewx.service.WxAccountService;
import com.coffeewx.wxmp.config.WxMpConfig;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpUserService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
* Created by CodeGenerator on 2019/01/16.
*/
@RestController
@RequestMapping("/wx/account/fans")
public class WxAccountFansController extends AbstractController{
    @Resource
    private WxAccountFansService wxAccountFansService;

    @Autowired
    private WxAccountService wxAccountService;

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
    public Result syncAccountFans(@RequestBody WxAccount wxAccount) {
        if(wxAccount.getId() == null){
            return ResultGenerator.genFailResult( "参数不全" );
        }
        wxAccount = wxAccountService.findById( wxAccount.getId() );
        WxMpService wxMpService = WxMpConfig.getMpServices().get( wxAccount.getAppid() );
        WxMpUserService wxMpUserService = wxMpService.getUserService();

        String nextOpenid = null;
        try {
            WxMpUserList wxMpUserList = wxMpUserService.userList( nextOpenid );
            List<WxMpUser> wxMpUsers = wxMpUserService.userInfoList( wxMpUserList.getOpenids() );
            for (int i = 0; i < wxMpUsers.size(); i++) {
                WxMpUser wxmpUser = wxMpUsers.get( i );
                logger.info( "wxMpUsers : {}", JSON.toJSONString(wxmpUser) );
                WxAccountFans wxAccountFans = wxAccountFansService.findBy( "openid", wxmpUser.getOpenId() );
                if(wxAccountFans == null){//insert
                    wxAccountFans = new WxAccountFans();
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
                    wxAccountFans.setCreateTime( DateUtil.date() );
                    wxAccountFans.setUpdateTime( DateUtil.date() );
                    wxAccountFansService.save( wxAccountFans );
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
                    wxAccountFansService.update( wxAccountFans );
                }
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult( e.getMessage() );
        }
        return ResultGenerator.genSuccessResult();
    }

}
