package com.coffeewx.web;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.coffeewx.model.User;
import com.coffeewx.model.vo.UserInfoVO;
import com.coffeewx.model.vo.UserTreeNode;
import com.coffeewx.service.UserService;
import com.coffeewx.utils.UserUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2019/01/16.
*/
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        User userTemp = userService.findBy( "username", user.getUsername());
        if(userTemp != null && StringUtils.isNotBlank( userTemp.getId() + "" )){
            return ResultGenerator.genFailResult( "登陆名重复，添加失败！" );
        }
        user.setPwd( passwordEncoder.encode(user.getPwd()) );
        user.setCreateTime( DateUtil.date() );
        user.setUpdateTime( DateUtil.date() );
        userService.save(user);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        if(StringUtils.isNotBlank( user.getPwd() )){
            user.setPwd( passwordEncoder.encode(user.getPwd()) );
        }
        //编辑的时候，密码为空，不做更新
        if(StringUtils.isBlank( user.getPwd() )){
            user.setPwd( null );
        }
        user.setUpdateTime( DateUtil.date() );
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        User user = userService.findById(id);
        return ResultGenerator.genSuccessResult(user);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer limit,@RequestParam String username) {

        PageHelper.startPage(page, limit);
        User user = new User();
        user.setUsername( username );
        List<User> list = userService.findList(user);
        //前台展现数据，密码设置为空
        for (int i = 0; i < list.size(); i++) {
            list.get( i ).setPwd( "" );
        }
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/getUserInfo")
    public Result getUserInfo() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfoVO userInfoVO = userService.getUserInfo( userDetails.getUsername() );
        return ResultGenerator.genSuccessResult(userInfoVO);
    }

    @PostMapping("/listTreeUser")
    public Result listTreeUser() {
        User user = new User();
        List<User> list = userService.findList(user);
        List<UserTreeNode> userTreeNodeList = Lists.newArrayList();
        list.forEach( temp -> {
            UserTreeNode userTreeNode = new UserTreeNode();
            BeanUtil.copyProperties( temp,userTreeNode );
            userTreeNodeList.add( userTreeNode );
        } );
        JSONObject json = new JSONObject(  );
        json.put( "userList", list);
        json.put( "userTree", userTreeNodeList);
        return ResultGenerator.genSuccessResult(json);
    }

}
