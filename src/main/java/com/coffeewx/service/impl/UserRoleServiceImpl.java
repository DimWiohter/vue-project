package com.coffeewx.service.impl;

import com.coffeewx.dao.UserRoleMapper;
import com.coffeewx.model.UserRole;
import com.coffeewx.service.UserRoleService;
import com.coffeewx.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by CodeGenerator on 2019/03/27.
 */
@Service
@Transactional
public class UserRoleServiceImpl extends AbstractService<UserRole> implements UserRoleService {
    @Autowired
    private UserRoleMapper sysUserRoleMapper;

    @Override
    public List<UserRole> findList(UserRole sysUserRole){
        return sysUserRoleMapper.findList(sysUserRole);
    }

}
