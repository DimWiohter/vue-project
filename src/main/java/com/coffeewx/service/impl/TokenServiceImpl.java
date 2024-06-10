package com.coffeewx.service.impl;

import cn.hutool.core.util.IdUtil;
import com.google.common.collect.Maps;
import com.coffeewx.core.ProjectConstant;
import com.coffeewx.core.redis.CacheService;
import com.coffeewx.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Token接口实现
 * @author Kevin
 * @date 2018-12-11 17:41
 */
@Service
public class TokenServiceImpl implements TokenService{

    @Autowired
    CacheService cacheService;

    @Override
    public String createToken(String userId) {
        String token = IdUtil.simpleUUID();
        String tokenKey = ProjectConstant.USER_TOKEN_PREFIX + token;
        Map<String, String> userinfoMap = Maps.newHashMap();
        userinfoMap.put( "userId", userId );
        cacheService.cacheMap( tokenKey, userinfoMap, ProjectConstant.USER_TOKEN_EXPIRE);
        return token;
    }

    @Override
    public boolean checkToken(String token) {
        if(StringUtils.isBlank( token )){
            return false;
        }
        String tokenKey = ProjectConstant.USER_TOKEN_PREFIX + token;
        Map <String, String> userInfoMap = cacheService.getMap( tokenKey );
        if (null == userInfoMap || userInfoMap.isEmpty()) {
            return false;
        } else {
            // 有操作 更新token过期时间
            cacheService.getRedisTemplate().expire( tokenKey, ProjectConstant.USER_TOKEN_EXPIRE, TimeUnit.SECONDS );
            return true;
        }
    }

    @Override
    public void deleteToken(String token) {
        String tokenKey = ProjectConstant.USER_TOKEN_PREFIX + token;
        cacheService.remove( tokenKey );
    }

    @Override
    public String getUserIdByToken(String token) {
        if(StringUtils.isBlank( token )){
            return null;
        }
        String tokenKey = ProjectConstant.USER_TOKEN_PREFIX + token;
        Map <String, String> userInfoMap = cacheService.getMap( tokenKey );
        if (null == userInfoMap || userInfoMap.isEmpty()) {
            return null;
        } else {
            // 有操作 更新token过期时间
            cacheService.getRedisTemplate().expire( tokenKey, ProjectConstant.USER_TOKEN_EXPIRE, TimeUnit.SECONDS );
            return userInfoMap.get( "userId" );
        }
    }
}
