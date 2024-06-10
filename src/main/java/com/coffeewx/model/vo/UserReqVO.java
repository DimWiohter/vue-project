package com.coffeewx.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户模型
 * @author Kevin
 * @date 2018-12-11 18:10
 */
@Data
public class UserReqVO implements Serializable {

    private String username;
    private String password;

}
