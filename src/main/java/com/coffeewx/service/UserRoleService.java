package com.coffeewx.service;

import com.coffeewx.core.Service;
import com.coffeewx.model.UserRole;

import java.util.List;

/**
 * Created by CodeGenerator on 2019/03/27.
 */
public interface UserRoleService extends Service<UserRole> {

    List<UserRole> findList(UserRole sysUserRole);

}
