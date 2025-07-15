package com.coffeewx.service;
import com.coffeewx.model.UserDept;
import com.coffeewx.core.Service;
import java.util.List;

/**
 * Created by CodeGenerator on 2019/03/27.
 */
public interface UserDeptService extends Service<UserDept> {

    List<UserDept> findList(UserDept sysUserDept);

}
