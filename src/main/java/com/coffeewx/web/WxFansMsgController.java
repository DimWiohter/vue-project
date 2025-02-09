package com.coffeewx.web;
import cn.hutool.core.date.DateUtil;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.coffeewx.model.User;
import com.coffeewx.model.WxAccount;
import com.coffeewx.model.WxFansMsg;
import com.coffeewx.model.WxFansMsgRes;
import com.coffeewx.service.UserService;
import com.coffeewx.service.WxAccountService;
import com.coffeewx.service.WxFansMsgResService;
import com.coffeewx.service.WxFansMsgService;
import com.coffeewx.utils.BaseContextHandler;
import com.coffeewx.wxmp.config.WxMpConfig;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpKefuService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpUserService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
* Created by CodeGenerator on 2019/03/13.
*/
@RestController
@RequestMapping("/wx/fans/msg")
public class WxFansMsgController extends AbstractController{
    @Autowired
    private WxFansMsgService wxFansMsgService;

    @Autowired
    private WxFansMsgResService wxFansMsgResService;

    @Autowired
    private UserService userService;

    @Autowired
    private WxAccountService wxAccountService;

    @PostMapping("/add")
    public Result add(@RequestBody WxFansMsg wxFansMsg) {
        wxFansMsgService.save(wxFansMsg);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        wxFansMsgService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody WxFansMsg wxFansMsg) {
        wxFansMsgService.update(wxFansMsg);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/updateResContent")
    public Result updateResContent(@RequestBody WxFansMsg wxFansMsg) {

        WxFansMsgRes wxFansMsgRes = new WxFansMsgRes();
        wxFansMsgRes.setResContent( wxFansMsg.getResContent() );
        wxFansMsgRes.setFansMsgId( String.valueOf( wxFansMsg.getId() ) );
        if(StringUtils.isNotBlank( BaseContextHandler.getUserID() )){
            User user = userService.findById( Integer.parseInt( BaseContextHandler.getUserID() ) );
            wxFansMsgRes.setUserId( BaseContextHandler.getUserID() );
            wxFansMsgRes.setUserName( user.getUsername() );
        }
        wxFansMsgRes.setCreateTime( DateUtil.date() );
        wxFansMsgRes.setUpdateTime( DateUtil.date() );
        wxFansMsgResService.save( wxFansMsgRes );


        wxFansMsg.setUpdateTime( DateUtil.date() );
        wxFansMsgService.update(wxFansMsg);

        WxFansMsg wxFansMsg2 = wxFansMsgService.findById( wxFansMsg.getId() );
        WxAccount wxAccount = wxAccountService.findById( Integer.parseInt( wxFansMsg2.getWxAccountId() ) );
        WxMpService wxMpService = WxMpConfig.getMpServices().get( wxAccount.getAppid() );
        WxMpKefuService wxMpKefuService = wxMpService.getKefuService();
        WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
        wxMpKefuMessage.setToUser(wxFansMsg2.getOpenid());
        wxMpKefuMessage.setMsgType( WxConsts.KefuMsgType.TEXT);
        wxMpKefuMessage.setContent(wxFansMsg2.getResContent());
        try {
            wxMpKefuService.sendKefuMessage( wxMpKefuMessage );
        } catch (WxErrorException e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult( e.getMessage() );
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        WxFansMsg wxFansMsg = wxFansMsgService.findById(id);
        return ResultGenerator.genSuccessResult(wxFansMsg);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer limit,@RequestParam String wxAccountId) {
        PageHelper.startPage(page, limit);
        WxFansMsg wxFansMsg = new WxFansMsg();
        wxFansMsg.setWxAccountId( wxAccountId );
        List<WxFansMsg> list = wxFansMsgService.findList( wxFansMsg );
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
