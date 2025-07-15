package com.coffeewx.dao;

import com.coffeewx.core.Mapper;
import com.coffeewx.model.RolePermission;
import com.coffeewx.model.RoleWxaccount;

import java.util.List;

public interface RoleWxaccountMapper extends Mapper<RoleWxaccount> {

    List<RoleWxaccount> findList(RoleWxaccount roleWxaccount);

}