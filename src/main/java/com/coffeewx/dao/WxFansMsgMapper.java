package com.coffeewx.dao;

import com.coffeewx.core.Mapper;
import com.coffeewx.model.WxFansMsg;

import java.util.List;

public interface WxFansMsgMapper extends Mapper<WxFansMsg> {

    List<WxFansMsg> findList(WxFansMsg tWxFansMsg);

}