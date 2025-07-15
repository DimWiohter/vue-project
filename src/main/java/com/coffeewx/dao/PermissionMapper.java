package com.coffeewx.dao;

import com.coffeewx.core.Mapper;
import com.coffeewx.model.Permission;

import java.util.List;

public interface PermissionMapper extends Mapper<Permission> {

    List<Permission> findList(Permission permission);

}