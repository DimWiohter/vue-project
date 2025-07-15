package com.coffeewx.dao;

import com.coffeewx.core.Mapper;
import com.coffeewx.model.Dept;

import java.util.List;

public interface DeptMapper extends Mapper<Dept>{

    List<Dept> findList(Dept dept);

}