package com.coffeewx.dao;

import com.coffeewx.core.Mapper;
import com.coffeewx.model.WxNewsTemplate;

import java.util.List;

public interface WxNewsTemplateMapper extends Mapper<WxNewsTemplate> {

    List<WxNewsTemplate> findList(WxNewsTemplate wxNewsTemplate);

}