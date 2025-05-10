package com.coffeewx.service;

import com.coffeewx.core.Service;
import com.coffeewx.model.WxAccount;
import com.coffeewx.model.WxAccountFans;
import com.coffeewx.model.vo.FansMsgVO;

import java.util.List;


/**
 * Created by CodeGenerator on 2019/01/16.
 */
public interface WxAccountFansService extends Service<WxAccountFans> {

    List<WxAccountFans> findList(WxAccountFans wxAccount);

    void syncAccountFans(WxAccount wxAccount) throws Exception;

    void sendMsg(FansMsgVO fansMsgVO) throws Exception;

}
