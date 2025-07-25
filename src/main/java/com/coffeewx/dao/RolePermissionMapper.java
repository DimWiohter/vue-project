package com.coffeewx.dao;

import com.coffeewx.core.Mapper;
import com.coffeewx.model.RolePermission;

import java.util.List;

public interface RolePermissionMapper extends Mapper<RolePermission> {

    List<RolePermission> findList(RolePermission rolePermission);

    void deleteByRoleId(String roleId);

}