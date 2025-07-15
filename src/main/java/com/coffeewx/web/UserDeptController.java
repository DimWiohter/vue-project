package com.coffeewx.web;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.coffeewx.model.UserDept;
import com.coffeewx.service.UserDeptService;
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
@RequestMapping("/user/dept")
public class UserDeptController extends AbstractController{
    @Autowired
    private UserDeptService userDeptService;

    @PostMapping("/add")
    public Result add(@RequestBody UserDept userDept) {
        userDeptService.save(userDept);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        userDeptService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody UserDept userDept) {
        userDeptService.update(userDept);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        UserDept userDept = userDeptService.findById(id);
        return ResultGenerator.genSuccessResult(userDept);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer limit) {
        PageHelper.startPage(page, limit);
        UserDept userDept = new UserDept();
        List<UserDept> list = userDeptService.findList(userDept);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
