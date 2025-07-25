package com.coffeewx.service.impl;

import com.coffeewx.common.jwt.JwtUser;
import com.coffeewx.dao.RoleMapper;
import com.coffeewx.model.Role;
import com.coffeewx.model.User;
import com.coffeewx.service.UserService;
import com.google.common.collect.Lists;
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

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findBy( "username", username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("'%s'.这个用户不存在", username));
        } else {
            List<Role> roleList = roleMapper.findListByUserId( String.valueOf( user.getId() ) );
            if(roleList == null){
                roleList = Lists.newArrayList();
            }
            List<SimpleGrantedAuthority> collect = roleList.stream().map( Role::getCode).map( SimpleGrantedAuthority::new).collect(Collectors.toList());
            return new JwtUser(String.valueOf( user.getId() ),user.getUsername(), user.getPwd(), Integer.valueOf( user.getFlag() ), collect);
        }
    }


}
