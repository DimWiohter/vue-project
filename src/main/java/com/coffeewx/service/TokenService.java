package com.coffeewx.service;

/**
 *  Token接口类
 * @author Kevin
 * @date 2018-12-11 17:28
 */
public interface TokenService {

    /**
     * 创建token
     * @param userId
     * @return java.lang.String
     * @author Kevin
     * @date 2018-12-11 17:39:13
     */
    String createToken(String userId);

    /**
     * 检查token是否有效
     * @param token
     * @return boolean
     * @author Kevin
     * @date 2018-12-11 17:39:21
     */
    boolean checkToken(String token);

    /**
     * 清除token
     * @param token
     * @return void
     * @author Kevin
     * @date 2018-12-11 17:40:17
     */
    void deleteToken(String token);

    /**
     * 根据token获取用户ID
     * @param token
     * @return java.lang.String
     * @author Kevin
     * @date 2019-01-14 15:55:15
     */
    String getUserIdByToken(String token);

}
