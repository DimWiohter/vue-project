package com.coffeewx.service;

import com.coffeewx.core.Service;
import com.coffeewx.model.WxAccount;
import com.coffeewx.model.WxAccountFansTag;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

import java.util.List;

/**
 * Created by CodeGenerator on 2019/03/21.
 */
public interface WxAccountFansTagService extends Service<WxAccountFansTag> {

    List<WxAccountFansTag> findList(WxAccountFansTag tWxAccountFansTag);

    void processFansTags(WxAccount wxAccount,WxMpUser wxmpUser);

}
