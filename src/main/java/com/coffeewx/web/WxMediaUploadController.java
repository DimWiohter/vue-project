package com.coffeewx.web;

import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.coffeewx.model.WxMediaUpload;
import com.coffeewx.service.WxMediaUploadService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* Created by CodeGenerator on 2019/03/11.
*/
@RestController
@RequestMapping("/wx/media/upload")
public class WxMediaUploadController {
    @Autowired
    private WxMediaUploadService wxMediaUploadService;

    @PostMapping("/add")
    public Result add(@RequestBody WxMediaUpload wxMediaUpload) {
        wxMediaUploadService.save(wxMediaUpload);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        wxMediaUploadService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody WxMediaUpload wxMediaUpload) {
        wxMediaUploadService.update(wxMediaUpload);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        WxMediaUpload wxMediaUpload = wxMediaUploadService.findById(id);
        return ResultGenerator.genSuccessResult(wxMediaUpload);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer limit) {
        PageHelper.startPage(page, limit);
        List<WxMediaUpload> list = wxMediaUploadService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
