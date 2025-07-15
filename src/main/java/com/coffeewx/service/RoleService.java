package com.coffeewx.service;
import com.coffeewx.model.Role;
import com.coffeewx.core.Service;
import java.util.List;

/**
 * Created by CodeGenerator on 2019/03/27.
 */
public interface RoleService extends Service<Role> {

    List<Role> findList(Role sysRole);

}
