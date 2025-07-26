package com.coffeewx.web;

import com.coffeewx.annotation.IgnoreToken;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.coffeewx.model.vo.TokenReqVO;
import com.coffeewx.model.vo.UserReqVO;
import com.coffeewx.service.AuthService;
import com.coffeewx.service.TokenService;
import com.coffeewx.utils.BaseContextHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 授权模块
 * @author Kevin
 * @date 2018-12-11 17:24
 */
@RestController
@RequestMapping("/auth")
@IgnoreToken
public class AuthController extends AbstractController{

    @Autowired
    AuthService authService;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public Result login(@RequestBody UserReqVO userReqVO) {
        if(StringUtils.isBlank( userReqVO.getUsername() ) || StringUtils.isBlank( userReqVO.getPassword() )){
            return ResultGenerator.genFailResult( "参数不全" );
        }
        String token = authService.login( userReqVO );
        return ResultGenerator.genSuccessResult( new TokenReqVO(token) );
    }



    @PostMapping("/logout")
    public Result logout(HttpServletRequest request, HttpServletResponse response) {
        tokenService.deleteToken( BaseContextHandler.getToken() );
        return ResultGenerator.genSuccessResult( );
    }

}
