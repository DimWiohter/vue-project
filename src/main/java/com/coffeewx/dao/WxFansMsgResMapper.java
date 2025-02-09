package com.coffeewx.dao;

import com.coffeewx.core.Mapper;
import com.coffeewx.model.WxFansMsgRes;

import java.util.List;

public interface WxFansMsgResMapper extends Mapper<WxFansMsgRes> {

    List<WxFansMsgRes> findList(WxFansMsgRes tWxFansMsgRes);

}