package com.coffeewx.service;

import com.coffeewx.core.Service;
import com.coffeewx.model.WxSubscribeText;

import java.util.List;

/**
 * Created by CodeGenerator on 2019/01/22.
 */
public interface WxSubscribeTextService extends Service<WxSubscribeText> {

    List<WxSubscribeText> findList(WxSubscribeText tWxSubscribeText);

}
