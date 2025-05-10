package com.coffeewx.service;

import com.coffeewx.core.Service;
import com.coffeewx.model.WxReceiveText;

import java.util.List;

/**
 * Created by CodeGenerator on 2019/01/22.
 */
public interface WxReceiveTextService extends Service<WxReceiveText> {

    List<WxReceiveText> findList(WxReceiveText tWxReceiveText);

    List<WxReceiveText> findListByReceiveTest(WxReceiveText tWxReceiveText);


}
