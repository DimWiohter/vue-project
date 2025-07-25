package com.coffeewx.dao;

import com.coffeewx.core.Mapper;
import com.coffeewx.model.Role;

import java.util.List;

public interface RoleMapper extends Mapper<Role> {

    List<Role> findList(Role role);

    List<Role> findListByUserId(String userId);

}