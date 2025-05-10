package com.coffeewx.web;

import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.coffeewx.model.WxFansMsgRes;
import com.coffeewx.service.WxFansMsgResService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* Created by CodeGenerator on 2019/03/13.
*/
@RestController
@RequestMapping("/wx/fans/msg/res")
public class WxFansMsgResController extends AbstractController{
    @Autowired
    private WxFansMsgResService wxFansMsgResService;

    @PostMapping("/add")
    public Result add(@RequestBody WxFansMsgRes wxFansMsgRes) {
        wxFansMsgResService.save(wxFansMsgRes);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        wxFansMsgResService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody WxFansMsgRes wxFansMsgRes) {
        wxFansMsgResService.update(wxFansMsgRes);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        WxFansMsgRes wxFansMsgRes = wxFansMsgResService.findById(id);
        return ResultGenerator.genSuccessResult(wxFansMsgRes);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer limit) {
        PageHelper.startPage(page, limit);
        WxFansMsgRes wxFansMsgRes = new WxFansMsgRes();
        List<WxFansMsgRes> list = wxFansMsgResService.findList( wxFansMsgRes );
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
