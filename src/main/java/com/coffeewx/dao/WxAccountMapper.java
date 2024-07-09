package com.coffeewx.dao;

import com.coffeewx.core.Mapper;
import com.coffeewx.model.WxAccount;

import java.util.List;

public interface WxAccountMapper extends Mapper<WxAccount> {

    List<WxAccount> findList(WxAccount wxAccount);

}