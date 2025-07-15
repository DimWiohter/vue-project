package com.coffeewx.service.impl;

import com.coffeewx.dao.RoleMapper;
import com.coffeewx.model.Role;
import com.coffeewx.service.RoleService;
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
public class RoleServiceImpl extends AbstractService<Role> implements RoleService {
    @Autowired
    private RoleMapper sysRoleMapper;

    @Override
    public List<Role> findList(Role sysRole){
        return sysRoleMapper.findList(sysRole);
    }

}
