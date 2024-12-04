package com.coffeewx.service.impl;

import com.coffeewx.dao.UserMapper;
import com.coffeewx.model.User;
import com.coffeewx.service.UserService;
import com.coffeewx.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2019/01/16.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper sysUserMapper;

    @Override
    public List<User> findList(User user) {
        return sysUserMapper.findList( user );
    }
}
