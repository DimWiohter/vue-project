package com.coffeewx.web;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.coffeewx.model.WxAccountFansTag;
import com.coffeewx.service.WxAccountFansTagService;
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
* Created by CodeGenerator on 2019/03/21.
*/
@RestController
@RequestMapping("/wx/account/fans/tag")
public class WxAccountFansTagController extends AbstractController{
    @Autowired
    private WxAccountFansTagService wxAccountFansTagService;

    @PostMapping("/add")
    public Result add(@RequestBody WxAccountFansTag wxAccountFansTag) {
        wxAccountFansTagService.save(wxAccountFansTag);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        wxAccountFansTagService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody WxAccountFansTag wxAccountFansTag) {
        wxAccountFansTagService.update(wxAccountFansTag);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        WxAccountFansTag wxAccountFansTag = wxAccountFansTagService.findById(id);
        return ResultGenerator.genSuccessResult(wxAccountFansTag);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer limit) {
        PageHelper.startPage(page, limit);
        WxAccountFansTag wxAccountFansTag = new WxAccountFansTag();
        List<WxAccountFansTag> list = wxAccountFansTagService.findList(wxAccountFansTag);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
