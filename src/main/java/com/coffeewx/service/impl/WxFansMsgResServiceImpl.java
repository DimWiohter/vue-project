package com.coffeewx.service.impl;

import com.coffeewx.core.AbstractService;
import com.coffeewx.dao.WxFansMsgResMapper;
import com.coffeewx.model.WxFansMsgRes;
import com.coffeewx.service.WxFansMsgResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by CodeGenerator on 2019/03/13.
 */
@Service
@Transactional
public class WxFansMsgResServiceImpl extends AbstractService<WxFansMsgRes> implements WxFansMsgResService {
    @Autowired
    private WxFansMsgResMapper tWxFansMsgResMapper;

    @Override
    public List<WxFansMsgRes> findList(WxFansMsgRes tWxFansMsgRes){
        return tWxFansMsgResMapper.findList(tWxFansMsgRes);
    }

}
