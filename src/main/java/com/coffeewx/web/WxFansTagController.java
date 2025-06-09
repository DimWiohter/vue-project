package com.coffeewx.web;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.coffeewx.model.WxFansTag;
import com.coffeewx.service.WxFansTagService;
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
@RequestMapping("/wx/fans/tag")
public class WxFansTagController extends AbstractController{
    @Autowired
    private WxFansTagService wxFansTagService;

    @PostMapping("/add")
    public Result add(@RequestBody WxFansTag wxFansTag) {
        wxFansTagService.save(wxFansTag);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        wxFansTagService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody WxFansTag wxFansTag) {
        wxFansTagService.update(wxFansTag);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        WxFansTag wxFansTag = wxFansTagService.findById(id);
        return ResultGenerator.genSuccessResult(wxFansTag);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer limit) {
        PageHelper.startPage(page, limit);
        WxFansTag wxFansTag = new WxFansTag();
        List<WxFansTag> list = wxFansTagService.findList(wxFansTag);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
