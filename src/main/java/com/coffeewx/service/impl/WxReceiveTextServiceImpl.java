package com.coffeewx.service.impl;

import com.coffeewx.dao.WxReceiveTextMapper;
import com.coffeewx.model.WxReceiveText;
import com.coffeewx.service.WxReceiveTextService;
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
public class WxReceiveTextServiceImpl extends AbstractService<WxReceiveText> implements WxReceiveTextService {
    @Autowired
    private WxReceiveTextMapper tWxReceiveTextMapper;

    @Override
    public List<WxReceiveText> findList(WxReceiveText tWxReceiveText){
        return tWxReceiveTextMapper.findList(tWxReceiveText);
    }

    @Override
    public List <WxReceiveText> findListByReceiveTest(WxReceiveText tWxReceiveText) {
        return tWxReceiveTextMapper.findListByReceiveTest(tWxReceiveText);
    }

}
