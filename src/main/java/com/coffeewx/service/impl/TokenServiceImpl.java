package com.coffeewx.service.impl;

import cn.hutool.core.util.IdUtil;
import com.coffeewx.core.ProjectConstant;
import com.coffeewx.core.redis.RedisUtils;
import com.coffeewx.model.User;
import com.coffeewx.service.TokenService;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Token接口实现
 * @author Kevin
 * @date 2018-12-11 17:41
 */
@Service
public class TokenServiceImpl implements TokenService{

    @Autowired
    RedisUtils redisUtils;

    @Override
    public String createToken(User user) {
        User cacheUser = new User();
        cacheUser.setId( user.getId() );
        cacheUser.setUsername( user.getUsername() );
        String token = IdUtil.simpleUUID();
        String tokenKey = ProjectConstant.USER_TOKEN_PREFIX + token;
        Map<String,Object> userMap = Maps.newHashMap();
        userMap.put( "userId",user.getId() );
        userMap.put( "username",user.getUsername() );
        redisUtils.hmset( tokenKey, userMap,ProjectConstant.USER_TOKEN_EXPIRE );
        return token;
    }

    @Override
    public boolean checkToken(String token) {
        if(StringUtils.isBlank( token )){
            return false;
        }
        String tokenKey = ProjectConstant.USER_TOKEN_PREFIX + token;
        if(redisUtils.hasKey( tokenKey )){
            // 有操作 更新token过期时间
            redisUtils.expire( tokenKey,ProjectConstant.USER_TOKEN_EXPIRE );
            return true;
        }
        return false;
    }

    @Override
    public void deleteToken(String token) {
        String tokenKey = ProjectConstant.USER_TOKEN_PREFIX + token;
        redisUtils.del( tokenKey );
    }

    @Override
    public User getUserByToken(String token) {
        if(StringUtils.isBlank( token )){
            return null;
        }
        String tokenKey = ProjectConstant.USER_TOKEN_PREFIX + token;
        Map<Object,Object> userMap = redisUtils.hmget( tokenKey );
        if(userMap == null){
            return null;
        }
        // 有操作 更新token过期时间
        redisUtils.expire( tokenKey,ProjectConstant.USER_TOKEN_EXPIRE );
        User user = new User();
        user.setId( Integer.valueOf( String.valueOf( userMap.get( "userId" ) ) ) );
        user.setUsername( String.valueOf( userMap.get( "username" ) ) );
        return user;
    }
}
