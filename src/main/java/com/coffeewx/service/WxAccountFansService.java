package com.coffeewx.service;
import com.coffeewx.model.WxAccountFans;
import com.coffeewx.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2019/01/16.
 */
public interface WxAccountFansService extends Service<WxAccountFans> {

    List<WxAccountFans> findList(WxAccountFans wxAccount);

}
