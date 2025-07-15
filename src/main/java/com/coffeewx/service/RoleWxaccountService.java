package com.coffeewx.service;
import com.coffeewx.model.RoleWxaccount;
import com.coffeewx.core.Service;
import java.util.List;

/**
 * Created by CodeGenerator on 2019/03/27.
 */
public interface RoleWxaccountService extends Service<RoleWxaccount> {

    List<RoleWxaccount> findList(RoleWxaccount sysRoleWxaccount);

}
