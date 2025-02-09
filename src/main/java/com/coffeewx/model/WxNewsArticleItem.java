package com.coffeewx.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;
@Data
@Table(name = "t_wx_news_article_item")
public class WxNewsArticleItem {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 摘要
     */
    private String digest;

    /**
     * 作者
     */
    private String author;

    /**
     * 是否展示封面图片（0/1）
     */
    @Column(name = "show_cover_pic")
    private String showCoverPic;

    /**
     * 上传微信，封面图片标识
     */
    @Column(name = "thumb_media_id")
    private String thumbMediaId;

    /**
     * 内容
     */
    private String content;

    /**
     * 内容链接
     */
    @Column(name = "content_source_url")
    private String contentSourceUrl;

    /**
     * 文章排序
     */
    @Column(name = "order_no")
    private Integer orderNo;

    /**
     * 图片路径
     */
    @Column(name = "pic_path")
    private String picPath;

    /**
     * 图文ID
     */
    @Column(name = "news_id")
    private String newsId;

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

    @Column(name = "need_open_comment")
    private String needOpenComment;

    @Column(name = "only_fans_can_comment")
    private String onlyFansCanComment;

}