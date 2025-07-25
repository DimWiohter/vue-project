package com.coffeewx.service.impl;

import com.coffeewx.dao.PermissionMapper;
import com.coffeewx.model.Permission;
import com.coffeewx.service.PermissionService;
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
public class PermissionServiceImpl extends AbstractService<Permission> implements PermissionService {
    @Autowired
    private PermissionMapper sysPermissionMapper;

    @Override
    public List<Permission> findList(Permission sysPermission){
        return sysPermissionMapper.findList(sysPermission);
    }

    @Override
    public List <Permission> listTreePermission() {
        return sysPermissionMapper.listTreePermission();
    }

}
