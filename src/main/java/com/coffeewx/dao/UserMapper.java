package com.coffeewx.dao;

import com.coffeewx.core.Mapper;
import com.coffeewx.model.User;

import java.util.List;

public interface UserMapper extends Mapper<User> {

    List<User> findList(User user);

}