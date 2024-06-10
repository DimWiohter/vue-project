package com.coffeewx.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.coffeewx.core.ServiceException;
import com.coffeewx.dao.UserMapper;
import com.coffeewx.model.User;
import com.coffeewx.model.vo.UserInfoVO;
import com.coffeewx.model.vo.UserReqVO;
import com.coffeewx.service.AuthService;
import com.coffeewx.service.TokenService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kevin
 * @date 2019-01-14 15:58
 */
@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    UserMapper userMapper;

    @Autowired
    TokenService tokenService;

    @Override
    public String login(UserReqVO userReqVO) {
        User userTpl = new User();
        userTpl.setUsername( userReqVO.getUsername() );
        User user = userMapper.getUserByUserName( userTpl );
        if(user == null){
            throw new ServiceException( "用户未注册" );
        }
        if(!user.getPwd().equals( SecureUtil.md5( userReqVO.getPassword() ) )){
            throw new ServiceException( "用户名或密码不正确" );
        }
        return tokenService.createToken( String.valueOf( user.getId() ) );
    }

    @Override
    public UserInfoVO getUserInfo(String token) {
        String userId = tokenService.getUserIdByToken( token );
        User user = userMapper.selectByPrimaryKey( Integer.parseInt( userId ) );
        if(user == null){
            throw new ServiceException( "用户不存在" );
        }
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setToken( token );
        userInfoVO.setAvatar( user.getAvatar() );
        userInfoVO.setIntroduction( user.getNickName() );
        userInfoVO.setName( user.getUsername() );
        List<String> roles = Lists.newArrayList();
        roles.add("admin");
        userInfoVO.setRoles( roles );
        return userInfoVO;
    }
}
