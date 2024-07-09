package com.coffeewx.dao;

import com.coffeewx.core.Mapper;
import com.coffeewx.model.WxAccountFans;

import java.util.List;

public interface WxAccountFansMapper extends Mapper<WxAccountFans> {

    List<WxAccountFans> findList(WxAccountFans wxAccountFans);

}