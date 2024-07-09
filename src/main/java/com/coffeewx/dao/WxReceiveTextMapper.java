package com.coffeewx.dao;

import com.coffeewx.core.Mapper;
import com.coffeewx.model.WxReceiveText;

import java.util.List;

public interface WxReceiveTextMapper extends Mapper<WxReceiveText> {

    List<WxReceiveText> findList(WxReceiveText wxReceiveText);

    List<WxReceiveText> findListByReceiveTest(WxReceiveText tWxReceiveText);

}