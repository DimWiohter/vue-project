package com.coffeewx.web;
import cn.hutool.core.date.DateUtil;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.coffeewx.model.WxReceiveText;
import com.coffeewx.service.WxReceiveTextService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
* Created by CodeGenerator on 2019/01/22.
*/
@RestController
@RequestMapping("/wx/receive/text")
public class WxReceiveTextController {
    @Autowired
    private WxReceiveTextService wxReceiveTextService;

    @PostMapping("/add")
    public Result add(@RequestBody WxReceiveText wxReceiveText) {
        WxReceiveText wxReceiveTextTpl = new WxReceiveText();
        wxReceiveTextTpl.setWxAccountId( wxReceiveText.getWxAccountId() );
        wxReceiveTextTpl.setReceiveText( wxReceiveText.getReceiveText() );
        List<WxReceiveText> wxReceiveTextList = wxReceiveTextService.findListByReceiveTest( wxReceiveTextTpl );
        if(wxReceiveTextList.size() > 0){
            return ResultGenerator.genFailResult( "每个公众号关键字不可重复！" );
        }

        wxReceiveText.setCreateTime( DateUtil.date() );
        wxReceiveText.setUpdateTime( DateUtil.date() );
        wxReceiveTextService.save(wxReceiveText);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        wxReceiveTextService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody WxReceiveText wxReceiveText) {
        wxReceiveText.setUpdateTime( DateUtil.date() );
        wxReceiveTextService.update(wxReceiveText);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        WxReceiveText wxReceiveText = wxReceiveTextService.findById(id);
        return ResultGenerator.genSuccessResult(wxReceiveText);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer limit,@RequestParam String receiveText,@RequestParam String wxAccountId) {
        PageHelper.startPage(page, limit);
        WxReceiveText wxReceiveText = new WxReceiveText();
        wxReceiveText.setReceiveText( receiveText );
        wxReceiveText.setWxAccountId( wxAccountId );
        List<WxReceiveText> list = wxReceiveTextService.findList( wxReceiveText );
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
