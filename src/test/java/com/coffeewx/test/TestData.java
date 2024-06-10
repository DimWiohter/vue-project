package com.coffeewx.test;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import com.coffeewx.Tester;
import com.coffeewx.model.User;
import com.coffeewx.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 测试类
 * @author Kevin
 * @date 2019-01-10 16:41
 */
public class TestData extends Tester{

    @Autowired
    UserService userService;

    @Test
    public void initUserTest(){
        User user = new User();
        user.setUsername( "admin" );
        user.setPwd( SecureUtil.md5( "admin" ) );
        user.setNickName( "Kevin" );
        user.setSex( "0" );
        user.setAvatar( "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif" );
        user.setFlag( "0" );
        user.setCreateTime( DateUtil.date() );
        user.setUpdateTime( DateUtil.date() );
        userService.save( user );
    }


}
