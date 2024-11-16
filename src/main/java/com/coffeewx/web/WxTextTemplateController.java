package com.coffeewx.web;
import cn.hutool.core.date.DateUtil;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.coffeewx.model.WxTextTemplate;
import com.coffeewx.service.WxTextTemplateService;
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
* Created by CodeGenerator on 2019/01/17.
*/
@RestController
@RequestMapping("/wx/text/template")
public class WxTextTemplateController {
    @Autowired
    private WxTextTemplateService wxTextTemplateService;

    @PostMapping("/add")
    public Result add(@RequestBody WxTextTemplate wxTextTemplate) {
        wxTextTemplate.setCreateTime( DateUtil.date() );
        wxTextTemplate.setUpdateTime( DateUtil.date() );
        wxTextTemplateService.save(wxTextTemplate);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        wxTextTemplateService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody WxTextTemplate wxTextTemplate) {
        wxTextTemplate.setUpdateTime( DateUtil.date() );
        wxTextTemplateService.update(wxTextTemplate);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        WxTextTemplate wxTextTemplate = wxTextTemplateService.findById(id);
        return ResultGenerator.genSuccessResult(wxTextTemplate);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer limit,@RequestParam String tplName) {
        PageHelper.startPage(page, limit);
        WxTextTemplate wxTextTemplate = new WxTextTemplate();
        wxTextTemplate.setTplName( tplName );
        List<WxTextTemplate> list = wxTextTemplateService.findList( wxTextTemplate );
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/listAll")
    public Result listAll() {
        List<WxTextTemplate> list = wxTextTemplateService.findAll();
        return ResultGenerator.genSuccessResult(list);
    }

}
