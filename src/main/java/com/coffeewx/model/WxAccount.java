package com.coffeewx.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "t_wx_account")
public class WxAccount {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 公众号名称
     */
    private String name;

    /**
     * 公众号账户
     */
    private String account;

    /**
     * 公众号appid
     */
    private String appid;

    /**
     * 公众号密钥
     */
    private String appsecret;

    /**
     * 公众号url
     */
    private String url;

    /**
     * 公众号token
     */
    private String token;

    /**
     * 加密密钥
     */
    private String aeskey;

    private String qrUrl;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss",iso= DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "create_time")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss",iso= DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "update_time")
    private Date updateTime;
}