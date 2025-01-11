package com.coffeewx.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "t_wx_menu")
public class WxMenu {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 父ID
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 菜单名称
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 菜单类型 1文本消息；2图文消息；3网址链接;4小程序
     */
    @Column(name = "menu_type")
    private String menuType;

    @Column(name = "menu_level")
    private String menuLevel;


    /**
     * 模板ID
     */
    @Column(name = "tpl_id")
    private String tplId;

    /**
     * 菜单URL
     */
    @Column(name = "menu_url")
    private String menuUrl;

    /**
     * 排序
     */
    @Column(name = "menu_sort")
    private String menuSort;

    /**
     * 微信账号ID
     */
    @Column(name = "wx_account_id")
    private String wxAccountId;

    @Column(name = "miniprogram_appid")
    private String miniprogramAppid;

    @Column(name = "miniprogram_pagepath")
    private String miniprogramPagepath;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss",iso= DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "create_time")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss",iso= DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "update_time")
    private Date updateTime;

    //扩展字段
    @Transient
    private String tplName;//模板名称
    @Transient
    private String wxAccountName;//公众号名称

}