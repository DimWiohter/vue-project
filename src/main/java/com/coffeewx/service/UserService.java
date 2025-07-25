package com.coffeewx.service;

import com.coffeewx.core.Service;
import com.coffeewx.model.User;
import com.coffeewx.model.vo.UserInfoVO;

import java.util.List;


/**
 * Created by CodeGenerator on 2019/01/16.
 */
public interface UserService extends Service<User> {

    List<User> findList(User user);

    /**
     * 根据username获取用户信息
     * @param username
     * @return
     */
    UserInfoVO getUserInfo(String username);

}
