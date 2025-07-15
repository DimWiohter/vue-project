package com.coffeewx.dao;

import com.coffeewx.core.Mapper;
import com.coffeewx.model.UserDept;

import java.util.List;

public interface UserDeptMapper extends Mapper<UserDept> {

    List<UserDept> findList(UserDept userDept);

}