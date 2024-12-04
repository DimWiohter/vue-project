package com.coffeewx.service;
import com.coffeewx.model.User;
import com.coffeewx.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2019/01/16.
 */
public interface UserService extends Service<User> {

    List<User> findList(User user);

}
