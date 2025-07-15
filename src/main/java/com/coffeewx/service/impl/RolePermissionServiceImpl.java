package com.coffeewx.service.impl;

import com.coffeewx.dao.RolePermissionMapper;
import com.coffeewx.model.RolePermission;
import com.coffeewx.service.RolePermissionService;
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
public class RolePermissionServiceImpl extends AbstractService<RolePermission> implements RolePermissionService {
    @Autowired
    private RolePermissionMapper sysRolePermissionMapper;

    @Override
    public List<RolePermission> findList(RolePermission sysRolePermission){
        return sysRolePermissionMapper.findList(sysRolePermission);
    }

}
