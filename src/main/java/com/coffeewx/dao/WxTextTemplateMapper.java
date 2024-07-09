package com.coffeewx.dao;

import com.coffeewx.core.Mapper;
import com.coffeewx.model.WxTextTemplate;

import java.util.List;

public interface WxTextTemplateMapper extends Mapper<WxTextTemplate> {

    List<WxTextTemplate> findList(WxTextTemplate tWxTextTemplate);

}