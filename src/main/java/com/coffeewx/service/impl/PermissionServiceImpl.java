package com.coffeewx.service.impl;

import com.coffeewx.dao.PermissionMapper;
import com.coffeewx.model.Permission;
import com.coffeewx.model.RolePermission;
import com.coffeewx.service.PermissionService;
import com.coffeewx.core.AbstractService;
import com.coffeewx.service.RolePermissionService;
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

    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    public List<Permission> findList(Permission sysPermission){
        return sysPermissionMapper.findList(sysPermission);
    }

    @Override
    public List <Permission> listTreePermission() {
        return sysPermissionMapper.listTreePermission();
    }

    @Override
    public void deleteRelation(String sysPermissionId) throws Exception {
        //解除资源关联的角色
        RolePermission rolePermission = new RolePermission();
        rolePermission.setPermissionId( sysPermissionId );
        List<RolePermission> rolePermissionList = rolePermissionService.findList( rolePermission );
        if(rolePermissionList != null && rolePermissionList.size() > 0){
            rolePermissionList.forEach( temp ->{
                rolePermissionService.deleteById( temp.getId() );
            } );
        }
        this.deleteById( Integer.valueOf( sysPermissionId ) );
    }

}
