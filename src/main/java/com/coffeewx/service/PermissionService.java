package com.coffeewx.service;

import com.coffeewx.core.Service;
import com.coffeewx.model.Permission;
import com.coffeewx.model.WxMenu;

import java.util.List;

/**
 * Created by CodeGenerator on 2019/03/27.
 */
public interface PermissionService extends Service<Permission> {

    List<Permission> findList(Permission sysPermission);

    List<Permission> listTreePermission();

    void deleteRelation(String sysPermissionId) throws Exception;

}
