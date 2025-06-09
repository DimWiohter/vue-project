package com.coffeewx.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "t_wx_account_fans")
public class WxAccountFans {
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
     * 订阅状态
     */
    @Column(name = "subscribe_status")
    private String subscribeStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss",iso= DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "subscribe_time")
    private Date subscribeTime;

    /**
     * 性别
     */
    private String gender;

    /**
     * 语言
     */
    private String language;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 头像地址
     */
    @Column(name = "headimg_url")
    private String headimgUrl;

    /**
     * 备注
     */
    private String remark;

    @Column(name = "wx_account_id")
    private String wxAccountId;

    /**
     * 微信公众号appid
     */
    @Column(name = "wx_account_appid")
    private String wxAccountAppid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss",iso= DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "create_time")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss",iso= DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 昵称
     */
    private byte[] nickname;

    //扩展字段
    @Transient
    private String nicknameStr;

    public String getNicknameStr() {
        if(this.getNickname() != null){
            try {
                this.nicknameStr = new String(this.getNickname(),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return nicknameStr;
    }
    @Transient
    private String wxAccountName;//公众号名称
    @Transient
    private List<WxFansTag> tagList = Lists.newArrayList();

}