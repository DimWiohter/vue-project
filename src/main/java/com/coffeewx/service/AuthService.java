package com.coffeewx.service;

import com.coffeewx.model.vo.UserReqVO;

/**
 * 授权模块
 * @author Kevin
 * @date 2019-01-14 15:46
 */
public interface AuthService {

    /**
     * 用户登录获取token
     * @param userReqVO
     * @return com.coffeewx.model.vo.UserInfoVO
     * @author Kevin
     * @date 2019-01-14 15:48:31
     */
    String login(UserReqVO userReqVO);

}
