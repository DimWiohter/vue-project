package com.coffeewx.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.coffeewx.core.ProjectConstant;
import com.coffeewx.core.ResultCode;
import com.coffeewx.core.ServiceException;
import com.coffeewx.dao.UserMapper;
import com.coffeewx.model.User;
import com.coffeewx.model.vo.UserInfoVO;
import com.coffeewx.model.vo.UserReqVO;
import com.coffeewx.service.AuthService;
import com.coffeewx.service.TokenService;
import com.coffeewx.service.UserService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
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
    UserService userService;

    @Autowired
    TokenService tokenService;

    @Override
    public String login(UserReqVO userReqVO) {
        User user = userService.findBy( "username",userReqVO.getUsername());
        if(user == null){
            throw new ServiceException( "用户未注册" );
        }
        if(user.getFlag().equals( ProjectConstant.NO )){
            throw new ServiceException( "用户已停用" );
        }
        if(!user.getPwd().equals( SecureUtil.md5( userReqVO.getPassword() ) )){
            throw new ServiceException( "用户名或密码不正确" );
        }
        return tokenService.createToken( String.valueOf( user.getId() ) );
    }

    @Override
    public UserInfoVO getUserInfo(String token) {
        String userId = tokenService.getUserIdByToken( token );
        if(StringUtils.isBlank( userId )){
            throw new ServiceException( ResultCode.UNAUTHORIZED.getDesc() );
        }
        User user = userService.findById( Integer.parseInt( userId ) );
        if(user == null){
            throw new ServiceException( "用户不存在" );
        }
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setToken( token );
        userInfoVO.setAvatar( user.getAvatar() );
        userInfoVO.setIntroduction( user.getNickName() );
        userInfoVO.setName( user.getUsername() );
        List<String> roles = Lists.newArrayList();
        if(user.getUsername().equals( ProjectConstant.RoleConstant.ADMIN )){
            roles.add(ProjectConstant.RoleConstant.ADMIN);
        }else{
            roles.add(ProjectConstant.RoleConstant.EDITOR );
        }

        userInfoVO.setRoles( roles );
        return userInfoVO;
    }
}
