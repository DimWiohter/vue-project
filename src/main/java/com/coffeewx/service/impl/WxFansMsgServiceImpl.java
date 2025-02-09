package com.coffeewx.service.impl;

import com.coffeewx.dao.WxFansMsgMapper;
import com.coffeewx.model.WxFansMsg;
import com.coffeewx.service.WxFansMsgService;
import com.coffeewx.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by CodeGenerator on 2019/03/13.
 */
@Service
@Transactional
public class WxFansMsgServiceImpl extends AbstractService<WxFansMsg> implements WxFansMsgService {
    @Autowired
    private WxFansMsgMapper tWxFansMsgMapper;

    @Override
    public List<WxFansMsg> findList(WxFansMsg tWxFansMsg){
        return tWxFansMsgMapper.findList(tWxFansMsg);
    }

}
