package com.coffeewx.interceptor;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description:日志切面
 * @Author:Kevin
 * @Date:2018-12-07 15:09
 */
//@Profile({"dev", "test"})
@Component
@Aspect
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger( LogAspect.class );

    /**
     * 定义一个公共的方法，实现切入点
     * 拦截Controller下面的所有方法  任何参数(..表示拦截任何参数)
     * 以@RestController注解作为切入点  可切入其他业务模块的方法
     *
     * @within和@target针对类的注解,
     * @annotation是针对方法的注解，为自定义注解
     */
//    @Pointcut("execution(public * com.*.web..*.*(..))")
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void point() {

    }

    /**
     * 拦截方法之前的一段业务逻辑
     *
     * @param joinPoint
     */
    @Before("point()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        Map params = new LinkedHashMap( 10 );
        params.put( "uri", request.getRequestURI() ); // 获取请求的url
        //params.put( "method", request.getMethod() ); // 获取请求的方式
        params.put( "args", joinPoint.getArgs() ); // 请求参数
        //params.put( "className", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() ); // 获取类名和获取类方法
        params.put( "ip", getClientIp(request) ); // 获取请求的ip地址

        // 输出格式化后的json字符串
        //logger.info( "params:{}", JSON.toJSONString( params ) );
        //logger.info( "params:{}", JSON.toJSONString( params.get( "args" ) ) );
        logger.info( "params:{}", JSONUtil.toJsonStr(params) );

    }

    /**
     * 获取响应返回值  方法执行return之后
     */
    @AfterReturning(returning = "object", pointcut = "point()")
    public void doAfterReturning(Object object) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 会打印出一个对象，想打印出具体内容需要在定义模型处加上toString()
        //logger.info( "result:{}", object.toString() );
        logger.info( "result:{}", JSONUtil.toJsonStr(object) );
        ;
    }

    /**
     * 环绕通知  在方法的调用前、后执行
     */
    @Around("point()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        //开始时间
        long begin = System.currentTimeMillis();
        //方法环绕proceed结果
        Object obj = point.proceed();
        //结束时间
        long end = System.currentTimeMillis();
        //时间差
        long timeDiff = (end - begin);
        String msg = "方法性能分析: 执行耗时 {}毫秒，来自Dream PWJ的表情";
//        if (timeDiff < 200) {
//            logger.info( "方法性能分析: 执行耗时 {}毫秒，" + "\uD83D\uDE02", timeDiff );
//        } else {
//            logger.warn( "方法性能分析: 执行耗时 {}毫秒，" + "\uD83D\uDE31", timeDiff );
//        }
        return obj;
    }


    /**
     * 拦截方法之后的一段业务逻辑
     */
    @After("point()")
    public void doAfter() {
//        logger.info( "doAfter" );
    }

    public String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        logger.debug("x-forwarded-for = {}", ip);
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            logger.debug("Proxy-Client-IP = {}", ip);
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            logger.debug("WL-Proxy-Client-IP = {}", ip);
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            logger.debug("RemoteAddr-IP = {}", ip);
        }
        if(StringUtils.isNotBlank(ip)) {
            ip = ip.split(",")[0];
        }
        return ip;
    }

}
