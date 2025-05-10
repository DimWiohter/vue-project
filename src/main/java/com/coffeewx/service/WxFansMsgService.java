package com.coffeewx.service;

import com.coffeewx.core.Service;
import com.coffeewx.model.WxFansMsg;

import java.util.List;

/**
 * Created by CodeGenerator on 2019/03/13.
 */
public interface WxFansMsgService extends Service<WxFansMsg> {

    List<WxFansMsg> findList(WxFansMsg tWxFansMsg);

    void updateResContent(WxFansMsg wxFansMsg) throws Exception;

}
