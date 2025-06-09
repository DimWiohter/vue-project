package com.coffeewx.service;

import com.coffeewx.core.Service;
import com.coffeewx.model.WxAccount;
import com.coffeewx.model.WxFansTag;

import java.util.List;

/**
 * Created by CodeGenerator on 2019/03/21.
 */
public interface WxFansTagService extends Service<WxFansTag> {

    List<WxFansTag> findList(WxFansTag tWxFansTag);

    void syncFansTag(WxAccount wxAccount) throws Exception;

}
