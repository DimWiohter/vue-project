package com.coffeewx.utils;

import com.coffeewx.common.jwt.JwtUser;
import com.coffeewx.dao.RoleMapper;
import com.coffeewx.dao.UserMapper;
import com.coffeewx.dao.WxAccountMapper;
import com.coffeewx.model.Role;
import com.coffeewx.model.User;
import com.coffeewx.model.WxAccount;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 工具类
 * @author Kevin
 * @date 2019-03-29 11:44
 */
public class UserUtils {

    private static UserMapper userMapper = SpringContextUtil.getBean(UserMapper.class);
    private static RoleMapper roleMapper = SpringContextUtil.getBean(RoleMapper.class);
    private static WxAccountMapper wxAccountMapper = SpringContextUtil.getBean(WxAccountMapper.class);

    public static JwtUser getJwtUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JwtUser jwtUser = (JwtUser)userDetails;
        return jwtUser;
    }

    public static User getUesr(){
        JwtUser jwtUser = getJwtUser();
        User userTpl = new User();
        userTpl.setId( Integer.valueOf( jwtUser.getUserId() ) );
        userTpl.setUsername( jwtUser.getUsername() );
        return userMapper.selectByPrimaryKey(userTpl);
    }

    public static List<Role> getRoleList(){
        JwtUser jwtUser = getJwtUser();
        List<Role> roleList = roleMapper.findListByUserId( jwtUser.getUserId() );
        return roleList;
    }

    public static String getWxAccountIds(){
        JwtUser jwtUser = getJwtUser();
        Set<String> wxAccountIds = Sets.newHashSet();
        List<Role> roleList = getRoleList();
        if(roleList != null && roleList.size() > 0){
            roleList.forEach( role -> {
                List<WxAccount> wxAccountList = wxAccountMapper.findListByRoleId( jwtUser.getUserId() );
                if(wxAccountList != null && wxAccountList.size() > 0){
                    wxAccountList.forEach( wxAccount -> {
                        wxAccountIds.add( String.valueOf( wxAccount.getId() ) );
                    } );
                }
            } );
        }
        List<String> list = new ArrayList<>(wxAccountIds);
        return String.join(",", list);
    }

}
