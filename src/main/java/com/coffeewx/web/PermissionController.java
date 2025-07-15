package com.coffeewx.web;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.coffeewx.model.Permission;
import com.coffeewx.service.PermissionService;
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
@RequestMapping("/permission")
public class PermissionController extends AbstractController{
    @Autowired
    private PermissionService permissionService;

    @PostMapping("/add")
    public Result add(@RequestBody Permission permission) {
        permissionService.save(permission);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        permissionService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Permission permission) {
        permissionService.update(permission);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Permission permission = permissionService.findById(id);
        return ResultGenerator.genSuccessResult(permission);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer limit) {
        PageHelper.startPage(page, limit);
        Permission permission = new Permission();
        List<Permission> list = permissionService.findList(permission);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
