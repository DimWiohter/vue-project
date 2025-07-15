package com.coffeewx.service.impl;

import com.coffeewx.dao.RoleWxaccountMapper;
import com.coffeewx.model.RoleWxaccount;
import com.coffeewx.service.RoleWxaccountService;
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
public class RoleWxaccountServiceImpl extends AbstractService<RoleWxaccount> implements RoleWxaccountService {
    @Autowired
    private RoleWxaccountMapper sysRoleWxaccountMapper;

    @Override
    public List<RoleWxaccount> findList(RoleWxaccount sysRoleWxaccount){
        return sysRoleWxaccountMapper.findList(sysRoleWxaccount);
    }

}
