package com.coffeewx.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.coffeewx.core.AbstractService;
import com.coffeewx.core.ServiceException;
import com.coffeewx.dao.PermissionMapper;
import com.coffeewx.dao.RoleMapper;
import com.coffeewx.dao.UserMapper;
import com.coffeewx.model.Permission;
import com.coffeewx.model.Role;
import com.coffeewx.model.User;
import com.coffeewx.model.vo.ButtonVO;
import com.coffeewx.model.vo.MenuVO;
import com.coffeewx.model.vo.UserInfoVO;
import com.coffeewx.service.UserService;
import com.coffeewx.utils.TreeBuilder;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;


/**
 * Created by CodeGenerator on 2019/01/16.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<User> findList(User user) {
        return userMapper.findList( user );
    }

    @Override
    public UserInfoVO getUserInfo(String username) {
        User user = this.findBy( "username",username );
        if(user == null){
            throw new ServiceException( "用户不存在" );
        }

        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtil.copyProperties( user,userInfoVO);

        Set<String> roles = Sets.newHashSet();
        Set<MenuVO> menuVOS = Sets.newHashSet();
        Set<ButtonVO> buttonVOS = Sets.newHashSet();

        //查询某个用户的角色
        List<Role> roleList = roleMapper.findListByUserId( String.valueOf( user.getId() ) );
        if(roleList != null && roleList.size() > 0){
            roleList.forEach( temp -> {
                roles.add( temp.getCode() );

                //查询某个角色的资源
                List<Permission> permissionList = permissionMapper.findListByRoleId( String.valueOf( temp.getId() ) );
                if(permissionList != null && permissionList.size() > 0){
                    permissionList.forEach( permission -> {
                        if (permission.getType().toLowerCase().equals("button")) {
                            //如果权限是按钮，就添加到按钮里面
                            ButtonVO buttonVO = new ButtonVO();
                            BeanUtil.copyProperties(permission, buttonVO);
                            buttonVOS.add( buttonVO );
                        }
                        if (permission.getType().toLowerCase().equals("menu")) {
                            //如果权限是菜单，就添加到菜单里面
                            MenuVO menuVO = new MenuVO();
                            BeanUtil.copyProperties(permission, menuVO);
                            menuVOS.add( menuVO );
                        }
                    } );
                }

            } );
        }

        userInfoVO.getRoles().addAll( roles );
        userInfoVO.getButtons().addAll( buttonVOS );
        userInfoVO.getMenus().addAll( TreeBuilder.buildTree(menuVOS) );
        return userInfoVO;
    }

}
