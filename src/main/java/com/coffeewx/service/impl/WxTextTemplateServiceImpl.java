package com.coffeewx.service.impl;

import com.coffeewx.dao.WxTextTemplateMapper;
import com.coffeewx.model.WxTextTemplate;
import com.coffeewx.service.WxTextTemplateService;
import com.coffeewx.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by CodeGenerator on 2019/01/17.
 */
@Service
@Transactional
public class WxTextTemplateServiceImpl extends AbstractService<WxTextTemplate> implements WxTextTemplateService {
    @Autowired
    private WxTextTemplateMapper tWxTextTemplateMapper;

    @Override
    public List<WxTextTemplate> findList(WxTextTemplate tWxTextTemplate){
        return tWxTextTemplateMapper.findList(tWxTextTemplate);
    }

}
