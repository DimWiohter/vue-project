package com.coffeewx.model;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;
@Data
@Table(name = "t_wx_fans_msg_res")
public class WxFansMsgRes {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fans_msg_id")
    private String fansMsgId;

    /**
     * 回复内容
     */
    @Column(name = "res_content")
    private String resContent;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

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