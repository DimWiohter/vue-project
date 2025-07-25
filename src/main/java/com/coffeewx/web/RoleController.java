package com.coffeewx.web;
import cn.hutool.core.date.DateUtil;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.coffeewx.model.Role;
import com.coffeewx.model.RolePermission;
import com.coffeewx.model.RoleWxaccount;
import com.coffeewx.model.UserRole;
import com.coffeewx.service.RolePermissionService;
import com.coffeewx.service.RoleService;
import com.coffeewx.service.RoleWxaccountService;
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
@RequestMapping("/role")
public class RoleController extends AbstractController{
    @Autowired
    private RoleService roleService;
    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleWxaccountService roleWxaccountService;

    @PostMapping("/add")
    public Result add(@RequestBody Role role) {
        Role role2 = roleService.findBy( "code",role.getCode());
        if(role2 != null){
            return ResultGenerator.genFailResult( "角色编码重复，添加失败！" );
        }
        role.setCreateTime( DateUtil.date() );
        role.setUpdateTime( DateUtil.date() );
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
        role.setUpdateTime( DateUtil.date() );
        roleService.update(role);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Role role = roleService.findById(id);
        return ResultGenerator.genSuccessResult(role);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer limit,@RequestParam String name) {
        PageHelper.startPage(page, limit);
        Role role = new Role();
        role.setName( name );
        List<Role> list = roleService.findList(role);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


    @PostMapping("/getRoleUserByRole")
    public Result getRoleUserByRole(@RequestParam String roleId) {
        UserRole userRole = new UserRole();
        userRole.setRoleId(  roleId );
        List<UserRole> list = userRoleService.findList(userRole);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/getRolePermissionByRole")
    public Result getRolePermissionByRole(@RequestParam String roleId) {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(  roleId );
        List<RolePermission> list = rolePermissionService.findList(rolePermission);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/getRoleWxAccountByRole")
    public Result getRoleWxAccountByRole(@RequestParam String roleId) {
        RoleWxaccount roleWxaccount = new RoleWxaccount();
        roleWxaccount.setRoleId(  roleId );
        List<RoleWxaccount> list = roleWxaccountService.findList(roleWxaccount);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/saveRoleUser")
    public Result saveRoleUser(@RequestParam String roleId,@RequestParam String ids) {
        roleService.saveRoleUser( roleId,ids );
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/saveRoleMenu")
    public Result saveRoleMenu(@RequestParam String roleId,@RequestParam String ids) {
        roleService.saveRoleMenu( roleId,ids );
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/saveRoleWxAccount")
    public Result saveRoleWxAccount(@RequestParam String roleId,@RequestParam String ids) {
        roleService.saveRoleWxAccount( roleId,ids );
        return ResultGenerator.genSuccessResult();
    }

}
