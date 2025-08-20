package com.coffeewx.configurer;

import com.alibaba.fastjson.JSON;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultCode;
import com.coffeewx.core.ServiceException;
import com.coffeewx.interceptor.TokenAnnotationInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Spring MVC 配置
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    private final Logger logger = LoggerFactory.getLogger( WebMvcConfigurer.class );
//    @Value("${spring.profiles.active}")
//    private String env;//当前激活的配置文件

    //使用阿里 FastJson 作为JSON MessageConverter
//    @Override
//    public void configureMessageConverters(List <HttpMessageConverter <?>> converters) {
//        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
//        FastJsonConfig config = new FastJsonConfig();
//        config.setSerializerFeatures( SerializerFeature.PrettyFormat,SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty );//保留空的字段
//        //SerializerFeature.WriteNullStringAsEmpty,//String null -> ""
//        //SerializerFeature.WriteNullNumberAsZero//Number null -> 0
//        // 按需配置，更多参考FastJson文档哈
//
//        converter.setFastJsonConfig( config );
//        converter.setDefaultCharset( Charset.forName( "UTF-8" ) );
//        converters.add( converter );
//    }


    //统一异常处理
    @Override
    public void configureHandlerExceptionResolvers(List <HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add( new HandlerExceptionResolver() {
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
                Result result = null;
                if (e instanceof ServiceException) {//业务失败的异常，如“账号或密码错误”
                    result = new Result( ResultCode.FAILURE ).setMessage( e.getMessage() );
                    logger.error( e.getMessage() );
                } else if (e instanceof NoHandlerFoundException) {
                    result = new Result( ResultCode.NOT_FOUND ).setMessage( "接口 [" + request.getRequestURI() + "] 不存在" );
                } else if (e instanceof ServletException) {
                    result = new Result( ResultCode.FAILURE ).setMessage( e.getMessage() );
                } else {
                    result = new Result( ResultCode.INTERNAL_SERVER_ERROR ).setMessage( "接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员" );
                    String message;
                    if (handler instanceof HandlerMethod) {
                        HandlerMethod handlerMethod = (HandlerMethod) handler;
                        message = String.format( "接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                                request.getRequestURI(),
                                handlerMethod.getBean().getClass().getName(),
                                handlerMethod.getMethod().getName(),
                                e.getMessage() );
                    } else {
                        message = e.getMessage();
                    }
                    logger.error( message, e );
                }

                responseResult( response, result );
                return new ModelAndView();
            }

        } );
    }

    //解决跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //registry.addMapping("/**");
    }

    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor( initTokenAnnotationInterceptor() ).addPathPatterns( "/**" );
        super.addInterceptors( registry );
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

    @Bean
    public TokenAnnotationInterceptor initTokenAnnotationInterceptor() {
        return new TokenAnnotationInterceptor();
    }

}
