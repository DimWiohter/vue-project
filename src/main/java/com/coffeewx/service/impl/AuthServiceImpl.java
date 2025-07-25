package com.coffeewx.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.coffeewx.common.jwt.JwtTokenUtil;
import com.coffeewx.core.ProjectConstant;
import com.coffeewx.core.ResultCode;
import com.coffeewx.core.ServiceException;
import com.coffeewx.model.User;
import com.coffeewx.model.vo.UserInfoVO;
import com.coffeewx.model.vo.UserReqVO;
import com.coffeewx.service.AuthService;
import com.coffeewx.service.TokenService;
import com.coffeewx.service.UserService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String login(UserReqVO userReqVO) {
        User user = userService.findBy( "username",userReqVO.getUsername());
        if(user == null){
            throw new ServiceException( "用户未注册" );
        }
        if(user.getFlag().equals( ProjectConstant.NO )){
            throw new ServiceException( "用户已停用" );
        }
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(userReqVO.getUsername(), userReqVO.getPassword());
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(userReqVO.getUsername());
        return jwtTokenUtil.generateToken(userDetails);
    }

}
