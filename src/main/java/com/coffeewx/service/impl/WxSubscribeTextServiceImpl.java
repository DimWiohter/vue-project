package com.coffeewx.service.impl;

import com.coffeewx.dao.WxSubscribeTextMapper;
import com.coffeewx.model.WxSubscribeText;
import com.coffeewx.service.WxSubscribeTextService;
import com.coffeewx.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by CodeGenerator on 2019/01/22.
 */
@Service
@Transactional
public class WxSubscribeTextServiceImpl extends AbstractService<WxSubscribeText> implements WxSubscribeTextService {
    @Autowired
    private WxSubscribeTextMapper tWxSubscribeTextMapper;

    @Override
    public List<WxSubscribeText> findList(WxSubscribeText tWxSubscribeText){
        return tWxSubscribeTextMapper.findList(tWxSubscribeText);
    }

}
