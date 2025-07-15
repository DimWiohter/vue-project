package com.coffeewx.service;
import com.coffeewx.model.RolePermission;
import com.coffeewx.core.Service;
import java.util.List;

/**
 * Created by CodeGenerator on 2019/03/27.
 */
public interface RolePermissionService extends Service<RolePermission> {

    List<RolePermission> findList(RolePermission sysRolePermission);

}
