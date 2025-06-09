package com.coffeewx.dao;

import com.coffeewx.core.Mapper;
import com.coffeewx.model.WxFansTag;
import com.coffeewx.model.WxMediaUpload;

import java.util.List;

public interface WxFansTagMapper extends Mapper<WxFansTag> {

    List<WxFansTag> findList(WxFansTag wxFansTag);

}