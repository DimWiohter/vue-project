package com.coffeewx.web;

import com.coffeewx.annotation.IgnoreToken;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.coffeewx.model.vo.TokenReqVO;
import com.coffeewx.model.vo.UserInfoVO;
import com.coffeewx.model.vo.UserReqVO;
import com.coffeewx.service.AuthService;
import com.coffeewx.service.TokenService;
import com.coffeewx.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    TokenService tokenService;

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public Result login(@RequestBody UserReqVO userReqVO) {
        if(StringUtils.isBlank( userReqVO.getUsername() ) || StringUtils.isBlank( userReqVO.getPassword() )){
            return ResultGenerator.genFailResult( "参数不全" );
        }
        String token = authService.login( userReqVO );
        return ResultGenerator.genSuccessResult( new TokenReqVO(token) );
    }

    @PostMapping("/getUserInfo")
    public Result getUserInfo(@RequestBody TokenReqVO tokenReqVO) {
        if(StringUtils.isBlank( tokenReqVO.getToken() )){
            return ResultGenerator.genFailResult( "参数不全" );
        }
        UserInfoVO userInfoVO = authService.getUserInfo( tokenReqVO.getToken() );
        return ResultGenerator.genSuccessResult(userInfoVO);
    }

    @PostMapping("/logout")
    public Result logout(@RequestParam String token) {
        tokenService.deleteToken( token );
        return ResultGenerator.genSuccessResult( );
    }

}
