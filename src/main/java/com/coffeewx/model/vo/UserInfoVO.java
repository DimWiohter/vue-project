package com.coffeewx.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息
 * @author Kevin
 * @date 2019-01-14 15:37
 */
@Data
public class UserInfoVO implements Serializable {
    private String token;
    private String introduction;
    private String avatar;
    private String name;
    private List<String> roles;
}
