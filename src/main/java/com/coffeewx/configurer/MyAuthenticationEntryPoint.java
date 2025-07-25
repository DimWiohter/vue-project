package com.coffeewx.configurer;

import com.coffeewx.core.Result;
import com.coffeewx.core.ResultCode;
import com.coffeewx.core.ResultGenerator;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 身份校验错误
 * @author Kevin
 * @date 2019-03-28 14:41
 */
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        ResultGenerator.responseResult( response,new Result( ResultCode.UNAUTHORIZED ) );
    }
}
