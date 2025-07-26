package com.coffeewx.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.coffeewx.core.ProjectConstant;
import com.coffeewx.core.ServiceException;
import com.coffeewx.model.User;
import com.coffeewx.model.vo.UserReqVO;
import com.coffeewx.service.AuthService;
import com.coffeewx.service.TokenService;
import com.coffeewx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if(!user.getPwd().equals(SecureUtil.md5( userReqVO.getPassword() ) )){
            throw new ServiceException( "用户名或密码不正确" );
        }
        return tokenService.createToken( user );
    }

}
