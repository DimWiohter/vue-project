package com.coffeewx.web;

import cn.hutool.core.date.DateUtil;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.coffeewx.model.WxSubscribeText;
import com.coffeewx.service.WxSubscribeTextService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* Created by CodeGenerator on 2019/01/22.
*/
@RestController
@RequestMapping("/wx/subscribe/text")
public class WxSubscribeTextController {
    @Autowired
    private WxSubscribeTextService wxSubscribeTextService;

    @PostMapping("/add")
    public Result add(@RequestBody WxSubscribeText wxSubscribeText) {
        WxSubscribeText wxSubscribeTextTpl = new WxSubscribeText();
        wxSubscribeTextTpl.setWxAccountId( wxSubscribeText.getWxAccountId() );
        List<WxSubscribeText> list = wxSubscribeTextService.findList( wxSubscribeTextTpl );
        if(list != null && list.size() > 0){
            return ResultGenerator.genFailResult( "每个公众号最多创建一条欢迎语" );
        }

        wxSubscribeText.setCreateTime( DateUtil.date() );
        wxSubscribeText.setUpdateTime( DateUtil.date() );
        wxSubscribeTextService.save(wxSubscribeText);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        wxSubscribeTextService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody WxSubscribeText wxSubscribeText) {
        wxSubscribeText.setUpdateTime( DateUtil.date() );
        wxSubscribeTextService.update(wxSubscribeText);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        WxSubscribeText wxSubscribeText = wxSubscribeTextService.findById(id);
        return ResultGenerator.genSuccessResult(wxSubscribeText);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer limit) {
        PageHelper.startPage(page, limit);
        WxSubscribeText wxSubscribeText = new WxSubscribeText();
        List<WxSubscribeText> list = wxSubscribeTextService.findList( wxSubscribeText );
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
