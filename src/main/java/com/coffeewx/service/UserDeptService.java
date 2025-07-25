package com.coffeewx.service;

import com.coffeewx.core.Service;
import com.coffeewx.model.UserDept;

import java.util.List;

/**
 * Created by CodeGenerator on 2019/03/27.
 */
public interface UserDeptService extends Service<UserDept> {

    List<UserDept> findList(UserDept sysUserDept);

}
