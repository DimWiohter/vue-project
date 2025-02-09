package com.coffeewx.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import javax.persistence.*;
@Data
@Table(name = "t_wx_fans_msg")
public class WxFansMsg {
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
     * 头像地址
     */
    @Column(name = "headimg_url")
    private String headimgUrl;

    /**
     * 微信账号ID
     */
    @Column(name = "wx_account_id")
    private String wxAccountId;

    /**
     * 消息类型
     */
    @Column(name = "msg_type")
    private String msgType;

    /**
     * 内容
     */
    private String content;

    /**
     * 最近一条回复内容
     */
    @Column(name = "res_content")
    private String resContent;

    /**
     * 是否已回复
     */
    @Column(name = "is_res")
    private String isRes;

    /**
     * 微信素材ID
     */
    @Column(name = "media_id")
    private String mediaId;

    /**
     * 微信图片URL
     */
    @Column(name = "pic_url")
    private String picUrl;

    /**
     * 本地图片路径
     */
    @Column(name = "pic_path")
    private String picPath;

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


}