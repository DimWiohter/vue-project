package com.coffeewx.core;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {

    public static Result genSuccessResult() {
        return new Result( ResultCode.SUCCESS );
    }

    public static <T> Result <T> genSuccessResult(T data) {
        return new Result( ResultCode.SUCCESS ).setData( data );
    }

    public static Result genFailResult() {
        return new Result( ResultCode.FAILURE );
    }

    public static Result genFailResult(String message) {
        return new Result( ResultCode.FAILURE ).setMessage( message );
    }

    public static void responseResult(HttpServletResponse response, Result result) {
        response.setCharacterEncoding( "UTF-8" );
        response.setHeader( "Content-type", "application/json;charset=UTF-8" );
        response.setStatus( 200 );
        try {
            response.getWriter().write( JSON.toJSONString( result ) );
        } catch (IOException ex) {
        }
    }

}
