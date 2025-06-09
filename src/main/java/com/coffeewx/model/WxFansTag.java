package com.coffeewx.model;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;
@Data
@Table(name = "t_wx_fans_tag")
public class WxFansTag {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标签名称
     */
    private String name;

    /**
     * 粉丝数量
     */
    private Integer count;

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

}