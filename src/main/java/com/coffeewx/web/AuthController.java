package com.coffeewx.web;

import cn.hutool.json.JSONUtil;
import com.coffeewx.annotation.IgnoreToken;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.coffeewx.model.vo.TokenReqVO;
import com.coffeewx.model.vo.UserReqVO;
import com.coffeewx.service.AuthService;
import com.coffeewx.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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



    @PostMapping("/logout")
    public Result logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ResultGenerator.genSuccessResult( );
    }

}
