package com.coffeewx.utils;

import com.coffeewx.dao.RoleMapper;
import com.coffeewx.dao.UserMapper;
import com.coffeewx.dao.WxAccountMapper;
import com.coffeewx.model.Role;
import com.coffeewx.model.User;
import com.coffeewx.model.WxAccount;
import com.google.common.collect.Sets;

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

    public static User getUesr(){
        User userTpl = new User();
        userTpl.setId( Integer.valueOf( BaseContextHandler.getUserID() ) );
        return userMapper.selectByPrimaryKey(userTpl);
    }

    public static List<Role> getRoleList(){
        List<Role> roleList = roleMapper.findListByUserId( BaseContextHandler.getUserID() );
        return roleList;
    }

    public static String getWxAccountIds(){
        Set<String> wxAccountIds = Sets.newHashSet();
        List<Role> roleList = getRoleList();
        if(roleList != null && roleList.size() > 0){
            roleList.forEach( role -> {
                List<WxAccount> wxAccountList = wxAccountMapper.findListByRoleId( BaseContextHandler.getUserID() );
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
