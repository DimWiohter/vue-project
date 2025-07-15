package com.coffeewx.service.impl;

import com.coffeewx.dao.DeptMapper;
import com.coffeewx.model.Dept;
import com.coffeewx.service.DeptService;
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
public class DeptServiceImpl extends AbstractService<Dept> implements DeptService {
    @Autowired
    private DeptMapper sysDeptMapper;

    @Override
    public List<Dept> findList(Dept sysDept){
        return sysDeptMapper.findList(sysDept);
    }

}
