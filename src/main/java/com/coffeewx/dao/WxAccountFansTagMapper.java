package com.coffeewx.dao;

import com.coffeewx.core.Mapper;
import com.coffeewx.model.WxAccountFansTag;
import com.coffeewx.model.WxFansTag;

import java.util.List;

public interface WxAccountFansTagMapper extends Mapper<WxAccountFansTag> {

    List<WxAccountFansTag> findList(WxAccountFansTag wxAccountFansTag);

}