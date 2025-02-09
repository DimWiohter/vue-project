package com.coffeewx.service;
import com.coffeewx.model.WxFansMsg;
import com.coffeewx.core.Service;
import java.util.List;

/**
 * Created by CodeGenerator on 2019/03/13.
 */
public interface WxFansMsgService extends Service<WxFansMsg> {

    List<WxFansMsg> findList(WxFansMsg tWxFansMsg);

}
