package com.coffeewx.service.impl;

import com.coffeewx.common.jwt.JwtUser;
import com.coffeewx.model.Role;
import com.coffeewx.model.User;
import com.coffeewx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Wang Chen Chen
 * @Date: 2018/10/29 14:15
 * @describe：
 * @version: 1.0
 */

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findBy( "username", username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("'%s'.这个用户不存在", username));
        } else {
            List<SimpleGrantedAuthority> collect = user.getRoleList().stream().map( Role::getCode).map( SimpleGrantedAuthority::new).collect(Collectors.toList());
            return new JwtUser(user.getUsername(), user.getPwd(), Integer.valueOf( user.getFlag() ), collect);
        }
    }


}
