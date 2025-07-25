package com.coffeewx.service.impl;

import cn.hutool.core.date.DateUtil;
import com.coffeewx.dao.RoleMapper;
import com.coffeewx.dao.RolePermissionMapper;
import com.coffeewx.dao.RoleWxaccountMapper;
import com.coffeewx.dao.UserRoleMapper;
import com.coffeewx.model.Role;
import com.coffeewx.model.RolePermission;
import com.coffeewx.model.RoleWxaccount;
import com.coffeewx.model.UserRole;
import com.coffeewx.service.RoleService;
import com.coffeewx.core.AbstractService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by CodeGenerator on 2019/03/27.
 */
@Service
@Transactional
public class RoleServiceImpl extends AbstractService<Role> implements RoleService {
    @Autowired
    private RoleMapper sysRoleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private RoleWxaccountMapper roleWxaccountMapper;


    @Override
    public List<Role> findList(Role sysRole){
        return sysRoleMapper.findList(sysRole);
    }

    @Override
    public List <Role> findListByUserId(String userId) {
        return null;
    }

    @Override
    public void saveRoleUser(String roleId, String ids) {
        userRoleMapper.deleteByRoleId( roleId );
        if(StringUtils.isNotBlank( ids )){
            String[] idArrays = ids.split( "," );
            if(idArrays.length > 0){
                for (String temp : idArrays) {
                    UserRole userRole = new UserRole();
                    userRole.setRoleId( roleId );
                    userRole.setUserId( temp );
                    userRole.setCreateTime( DateUtil.date() );
                    userRole.setUpdateTime( DateUtil.date() );
                    userRoleMapper.insert( userRole );
                }
            }
        }
    }

    @Override
    public void saveRoleMenu(String roleId, String ids) {
        rolePermissionMapper.deleteByRoleId( roleId );
        if(StringUtils.isNotBlank( ids )){
            String[] idArrays = ids.split( "," );
            if(idArrays.length > 0){
                for (String temp : idArrays) {
                    RolePermission rolePermission = new RolePermission();
                    rolePermission.setRoleId( roleId );
                    rolePermission.setPermissionId( temp );
                    rolePermission.setCreateTime( DateUtil.date() );
                    rolePermission.setUpdateTime( DateUtil.date() );
                    rolePermissionMapper.insert( rolePermission );
                }
            }
        }
    }

    @Override
    public void saveRoleWxAccount(String roleId, String ids) {
        roleWxaccountMapper.deleteByRoleId( roleId );
        if(StringUtils.isNotBlank( ids )){
            String[] idArrays = ids.split( "," );
            if(idArrays.length > 0){
                for (String temp : idArrays) {
                    RoleWxaccount roleWxaccount = new RoleWxaccount();
                    roleWxaccount.setRoleId( roleId );
                    roleWxaccount.setWxAccountId( temp );
                    roleWxaccount.setCreateTime( DateUtil.date() );
                    roleWxaccount.setUpdateTime( DateUtil.date() );
                    roleWxaccountMapper.insert( roleWxaccount );
                }
            }
        }
    }

}
