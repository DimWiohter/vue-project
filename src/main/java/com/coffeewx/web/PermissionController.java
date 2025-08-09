package com.coffeewx.web;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.coffeewx.model.Permission;
import com.coffeewx.model.vo.PermissionTreeNode;
import com.coffeewx.service.PermissionService;
import com.coffeewx.utils.TreeBuilder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        Permission permission2 = permissionService.findBy( "resources",permission.getResources() );
        if(permission2 != null){
            return ResultGenerator.genFailResult( "编码不可重复，添加失败！" );
        }
        permission.setCreateTime( DateUtil.date() );
        permission.setUpdateTime( DateUtil.date() );
        permissionService.save(permission);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) throws Exception{
        permissionService.deleteRelation(String.valueOf( id ));
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Permission permission) {
        permission.setUpdateTime( DateUtil.date() );
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

    @PostMapping("/listTreePermission")
    public Result listTreePermission() {
        List<Permission> list = permissionService.listTreePermission();
        List<PermissionTreeNode> permissionTreeNodeList = Lists.newArrayList();
        if(list != null && list.size() > 0){
            list.forEach( temp->{
                PermissionTreeNode permissionTreeNode = new PermissionTreeNode();
                BeanUtil.copyProperties( temp, permissionTreeNode);
                permissionTreeNodeList.add( permissionTreeNode );
            } );
        }
        List<PermissionTreeNode> permissionTreeNodeList2 = TreeBuilder.buildPermissionTree( permissionTreeNodeList );

        permissionTreeNodeList2.stream().sorted( Comparator.comparing( PermissionTreeNode::getSortNo ) ).collect( Collectors.toList());
        JSONObject json = new JSONObject(  );
        json.put( "menuList", list);
        json.put( "menuTree", permissionTreeNodeList2);
        return ResultGenerator.genSuccessResult(json);
    }


}
