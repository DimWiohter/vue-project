package com.coffeewx.service;

import com.coffeewx.core.Service;
import com.coffeewx.model.WxTextTemplate;

import java.util.List;

/**
 * Created by CodeGenerator on 2019/01/17.
 */
public interface WxTextTemplateService extends Service<WxTextTemplate> {

    List<WxTextTemplate> findList(WxTextTemplate tWxTextTemplate);

}
