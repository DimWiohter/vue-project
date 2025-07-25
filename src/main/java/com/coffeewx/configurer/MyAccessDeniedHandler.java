package com.coffeewx.configurer;

import com.alibaba.fastjson.JSON;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultCode;
import com.coffeewx.core.ResultGenerator;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义AccessDeniedHandler处理器、权限校验错误
 * @author Kevin
 * @date 2019-03-28 14:09
 */
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        ResultGenerator.responseResult( response,new Result( ResultCode.USER_UNAUTHORIZED ) );

    }
}
