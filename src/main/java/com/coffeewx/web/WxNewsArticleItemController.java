package com.coffeewx.web;

import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.coffeewx.model.WxNewsArticleItem;
import com.coffeewx.service.WxNewsArticleItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* Created by CodeGenerator on 2019/03/11.
*/
@RestController
@RequestMapping("/wx/news/article/item")
public class WxNewsArticleItemController {
    @Autowired
    private WxNewsArticleItemService wxNewsArticleItemService;

    @PostMapping("/add")
    public Result add(@RequestBody WxNewsArticleItem wxNewsArticleItem) {
        wxNewsArticleItemService.save(wxNewsArticleItem);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        wxNewsArticleItemService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody WxNewsArticleItem wxNewsArticleItem) {
        wxNewsArticleItemService.update(wxNewsArticleItem);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        WxNewsArticleItem wxNewsArticleItem = wxNewsArticleItemService.findById(id);
        return ResultGenerator.genSuccessResult(wxNewsArticleItem);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer limit) {
        PageHelper.startPage(page, limit);
        List<WxNewsArticleItem> list = wxNewsArticleItemService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
