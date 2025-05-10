package com.coffeewx.web;

import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.coffeewx.model.WxFansMsg;
import com.coffeewx.service.WxFansMsgService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* Created by CodeGenerator on 2019/03/13.
*/
@RestController
@RequestMapping("/wx/fans/msg")
public class WxFansMsgController extends AbstractController{
    @Autowired
    private WxFansMsgService wxFansMsgService;

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
    public Result updateResContent(@RequestBody WxFansMsg wxFansMsg) throws Exception{
        wxFansMsgService.updateResContent( wxFansMsg );
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
