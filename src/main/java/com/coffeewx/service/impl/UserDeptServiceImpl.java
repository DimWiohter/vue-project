package com.coffeewx.service.impl;

import com.coffeewx.dao.UserDeptMapper;
import com.coffeewx.model.UserDept;
import com.coffeewx.service.UserDeptService;
import com.coffeewx.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by CodeGenerator on 2019/03/27.
 */
@Service
@Transactional
public class UserDeptServiceImpl extends AbstractService<UserDept> implements UserDeptService {
    @Autowired
    private UserDeptMapper sysUserDeptMapper;

    @Override
    public List<UserDept> findList(UserDept sysUserDept){
        return sysUserDeptMapper.findList(sysUserDept);
    }

}
