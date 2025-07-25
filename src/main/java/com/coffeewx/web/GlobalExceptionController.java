package com.coffeewx.web;

import com.coffeewx.core.Result;
import com.coffeewx.core.ResultCode;
import com.coffeewx.core.ResultGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionController extends AbstractController{


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e) {
        logger.error(e.getMessage());
        return ResultGenerator.genFailResult( e.getMessage() );
    }

    /**
     * 认证错误
     */
    @ExceptionHandler(AuthenticationException.class)
    public Result handleAuthenticationException(AuthenticationException e) {
        logger.error(e.getMessage());
        return new Result( ResultCode.USER_UNAUTHORIZED );
    }

    /**
     * 400 - Bad Request
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Result handleValidationException(MethodArgumentNotValidException e) {
        logger.error(e.getMessage());
        return ResultGenerator.genFailResult( "请求类型不支持");
    }

    /**
     * 405 - Method Not Allowed。HttpRequestMethodNotSupportedException
     * 是ServletException的子类,需要Servlet API支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e) {
        logger.error(e.getMessage());
        return ResultGenerator.genFailResult( "不被支持的访问方法");
    }

    /**
     * 415 - Unsupported Media Type。HttpMediaTypeNotSupportedException
     * 是ServletException的子类,需要Servlet API支持
     */
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public Result handleHttpMediaTypeNotSupportedException(Exception e) {
        logger.error(e.getMessage());
        return ResultGenerator.genFailResult( "不支持的媒体类型");
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    public Result<String> globalException(HttpServletRequest request, Exception e) {
        return ResultGenerator.genFailResult( e.getMessage());
    }

}
