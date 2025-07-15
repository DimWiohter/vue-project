package com.coffeewx.dao;

import com.coffeewx.core.Mapper;
import com.coffeewx.model.UserRole;

import java.util.List;

public interface UserRoleMapper extends Mapper<UserRole> {

    List<UserRole> findList(UserRole userRole);

}