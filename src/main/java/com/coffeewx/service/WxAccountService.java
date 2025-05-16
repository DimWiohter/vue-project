package com.coffeewx.service;

import com.coffeewx.core.Service;
import com.coffeewx.model.WxAccount;

import java.util.List;


/**
 * Created by CodeGenerator on 2019/01/16.
 */
public interface WxAccountService extends Service<WxAccount> {

    List<WxAccount> findList(WxAccount wxAccount);

    void generateQRUrl(WxAccount wxAccount) throws Exception;

}
