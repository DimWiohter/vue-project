package com.coffeewx.service.impl;

import com.coffeewx.dao.WxNewsTemplateMapper;
import com.coffeewx.model.WxNewsTemplate;
import com.coffeewx.service.WxNewsTemplateService;
import com.coffeewx.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by CodeGenerator on 2019/03/11.
 */
@Service
@Transactional
public class WxNewsTemplateServiceImpl extends AbstractService<WxNewsTemplate> implements WxNewsTemplateService {
    @Autowired
    private WxNewsTemplateMapper tWxNewsTemplateMapper;

    @Override
    public List<WxNewsTemplate> findList(WxNewsTemplate tWxNewsTemplate){
        return tWxNewsTemplateMapper.findList(tWxNewsTemplate);
    }

}
