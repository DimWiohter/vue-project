package com.coffeewx.interceptor;

import com.alibaba.fastjson.JSON;
import com.coffeewx.annotation.IgnoreToken;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultCode;
import com.coffeewx.core.ServiceException;
import com.coffeewx.core.redis.CacheService;
import com.coffeewx.service.TokenService;
import com.coffeewx.utils.BaseContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class TokenAnnotationInterceptor extends HandlerInterceptorAdapter {

    private final Logger logger = LoggerFactory.getLogger( TokenAnnotationInterceptor.class );

    @Resource
    private CacheService cacheService;

    @Autowired
    TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handler2 = (HandlerMethod) handler;
            Method method = handler2.getMethod();
            logger.debug( "方法[" + method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()]拦截开始！" );
            try {
                Class <?> clazz = method.getDeclaringClass();
                if (method.isAnnotationPresent( IgnoreToken.class )
                        || clazz.isAnnotationPresent( IgnoreToken.class )) {
                    return true;
                }
                String token = request.getHeader( "X-Token" );
                if(!tokenService.checkToken( token )){
                    Result result = new Result( ResultCode.UNAUTHORIZED );
                    responseResult( response, result );
                    return false;
                }

                String userId = tokenService.getUserIdByToken( token );
                BaseContextHandler.setUserID( userId );
                logger.debug( "token: " + token );
            } catch (Exception e) {
                logger.error( "拦截出错！", e );
                throw new ServiceException( "拦截器异常！" );
            } finally {
                logger.debug( "方法[" + method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()]拦截结束！" );
            }
        }
        return true;

    }

    private void responseResult(HttpServletResponse response, Result result) {
        response.setCharacterEncoding( "UTF-8" );
        response.setHeader( "Content-type", "application/json;charset=UTF-8" );
        response.setStatus( 200 );
        try {
            response.getWriter().write( JSON.toJSONString( result ) );
        } catch (IOException ex) {
            logger.error( ex.getMessage() );
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContextHandler.remove();
        super.afterCompletion(request, response, handler, ex);
    }

}
