package com.coffeewx.dao;

import com.coffeewx.core.Mapper;
import com.coffeewx.model.WxSubscribeText;

import java.util.List;

public interface WxSubscribeTextMapper extends Mapper<WxSubscribeText> {

    List<WxSubscribeText> findList(WxSubscribeText wxSubscribeText);

}