package com.coffeewx.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;
@Data
@Table(name = "t_wx_news_template")
public class WxNewsTemplate {
    /**
     * 主键 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 模板名称
     */
    @Column(name = "tpl_name")
    private String tplName;

    /**
     * 是否已上传微信
     */
    @Column(name = "is_upload")
    private String isUpload;

    @Column(name = "media_id")
    private String mediaId;

    /**
     * 微信账号ID
     */
    @Column(name = "wx_account_id")
    private String wxAccountId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss",iso= DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "create_time")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss",iso= DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "update_time")
    private Date updateTime;

    @Transient
    private String wxAccountName;//公众号名称

    @Transient
    private Integer countArticle;//公众号名称

}