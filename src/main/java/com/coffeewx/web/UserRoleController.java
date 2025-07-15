package com.coffeewx.web;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.coffeewx.model.UserRole;
import com.coffeewx.service.UserRoleService;
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
@RequestMapping("/user/role")
public class UserRoleController extends AbstractController{
    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("/add")
    public Result add(@RequestBody UserRole userRole) {
        userRoleService.save(userRole);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        userRoleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody UserRole userRole) {
        userRoleService.update(userRole);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        UserRole userRole = userRoleService.findById(id);
        return ResultGenerator.genSuccessResult(userRole);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer limit) {
        PageHelper.startPage(page, limit);
        UserRole userRole = new UserRole();
        List<UserRole> list = userRoleService.findList(userRole);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
