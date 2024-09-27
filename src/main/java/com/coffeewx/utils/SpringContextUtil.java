package com.coffeewx.utils;

import com.coffeewx.wxmp.config.WxConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author Kevin
 * @date 2019-01-22 19:00
 */
@Component
@Order(2)
public class SpringContextUtil implements ApplicationContextAware {

    private final static Logger logger = LoggerFactory.getLogger(SpringContextUtil.class);

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringContextUtil.applicationContext == null) {
            SpringContextUtil.applicationContext = applicationContext;
        }
        System.out.println("========ApplicationContext配置成功,在普通类可以通过调用SpringUtils.getAppContext()获取applicationContext对象,applicationContext="+SpringContextUtil.applicationContext+"========");
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过name获取 Bean.
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz){
        logger.info( "1231236666" );
        Assert.notNull( getApplicationContext(),"12312311111111111111" );
        logger.info( "1111111111111111111111" );
        Assert.notNull( getApplicationContext(),"12312311111111111111" );
        logger.info( getApplicationContext().getApplicationName() );
        System.out.println(123123);
        logger.info( "111111" );
        Assert.notNull( getApplicationContext().getBean(clazz),"12312311111111111111" );
        logger.info( "234234" );
        Assert.isNull( getApplicationContext().getBean(clazz),"1231231133333333331111" );
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }

}
