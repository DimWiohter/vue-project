package com.coffeewx.service.impl;

import com.coffeewx.dao.WxAccountFansMapper;
import com.coffeewx.model.WxAccountFans;
import com.coffeewx.service.WxAccountFansService;
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
public class WxAccountFansServiceImpl extends AbstractService<WxAccountFans> implements WxAccountFansService {
    @Resource
    private WxAccountFansMapper tWxAccountFansMapper;

    @Override
    public List<WxAccountFans> findList(WxAccountFans wxAccountFans) {
        return tWxAccountFansMapper.findList( wxAccountFans );
    }

}
