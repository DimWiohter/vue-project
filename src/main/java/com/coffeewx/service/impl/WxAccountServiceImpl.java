package com.coffeewx.service.impl;

import com.coffeewx.dao.WxAccountMapper;
import com.coffeewx.model.WxAccount;
import com.coffeewx.service.WxAccountService;
import com.coffeewx.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2019/01/16.
 */
@Service
@Transactional
public class WxAccountServiceImpl extends AbstractService<WxAccount> implements WxAccountService {
    @Resource
    private WxAccountMapper tWxAccountMapper;

    @Override
    public List<WxAccount> findList(WxAccount wxAccount) {
        return tWxAccountMapper.findList( wxAccount );
    }
}
