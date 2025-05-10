package com.coffeewx.service;

import com.coffeewx.core.Service;
import com.coffeewx.model.User;

import java.util.List;


/**
 * Created by CodeGenerator on 2019/01/16.
 */
public interface UserService extends Service<User> {

    List<User> findList(User user);

}
