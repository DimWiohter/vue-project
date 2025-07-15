package com.coffeewx.web;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.coffeewx.model.Role;
import com.coffeewx.service.RoleService;
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
@RequestMapping("/role")
public class RoleController extends AbstractController{
    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public Result add(@RequestBody Role role) {
        roleService.save(role);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        roleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Role role) {
        roleService.update(role);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Role role = roleService.findById(id);
        return ResultGenerator.genSuccessResult(role);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer limit) {
        PageHelper.startPage(page, limit);
        Role role = new Role();
        List<Role> list = roleService.findList(role);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
