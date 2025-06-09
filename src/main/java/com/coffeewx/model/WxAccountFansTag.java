package com.coffeewx.model;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;
@Data
@Table(name = "t_wx_account_fans_tag")
public class WxAccountFansTag {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户标识
     */
    private String openid;

    /**
     * 标签ID
     */
    @Column(name = "tag_id")
    private String tagId;

    /**
     * 微信账号ID
     */
    @Column(name = "wx_account_id")
    private String wxAccountId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    //扩展字段
    @Transient
    private String name;

}