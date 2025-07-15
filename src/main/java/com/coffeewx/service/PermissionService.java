package com.coffeewx.service;
import com.coffeewx.model.Permission;
import com.coffeewx.core.Service;
import java.util.List;

/**
 * Created by CodeGenerator on 2019/03/27.
 */
public interface PermissionService extends Service<Permission> {

    List<Permission> findList(Permission sysPermission);

}
