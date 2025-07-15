package com.coffeewx.web;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.coffeewx.model.RoleWxaccount;
import com.coffeewx.service.RoleWxaccountService;
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
* Created by CodeGenerator on 2019/03/27.
*/
@RestController
@RequestMapping("/role/wxaccount")
public class RoleWxaccountController extends AbstractController{
    @Autowired
    private RoleWxaccountService roleWxaccountService;

    @PostMapping("/add")
    public Result add(@RequestBody RoleWxaccount roleWxaccount) {
        roleWxaccountService.save(roleWxaccount);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        roleWxaccountService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody RoleWxaccount roleWxaccount) {
        roleWxaccountService.update(roleWxaccount);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        RoleWxaccount roleWxaccount = roleWxaccountService.findById(id);
        return ResultGenerator.genSuccessResult(roleWxaccount);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer limit) {
        PageHelper.startPage(page, limit);
        RoleWxaccount roleWxaccount = new RoleWxaccount();
        List<RoleWxaccount> list = roleWxaccountService.findList(roleWxaccount);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
