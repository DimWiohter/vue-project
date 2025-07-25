package com.coffeewx.service;

import com.coffeewx.core.Service;
import com.coffeewx.model.Role;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by CodeGenerator on 2019/03/27.
 */
public interface RoleService extends Service<Role> {

    List<Role> findList(Role sysRole);

    List<Role> findListByUserId(String username);

    void saveRoleUser(String roleId, String ids);

    void saveRoleMenu(String roleId, String ids);

    void saveRoleWxAccount(String roleId, String ids);

}
