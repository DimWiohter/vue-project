package com.coffeewx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_wx_media_upload")
public class WxMediaUpload {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 类型
     */
    private String type;

    /**
     * 图片URL
     */
    private String url;

    /**
     * 素材ID
     */
    @Column(name = "media_id")
    private String mediaId;

    /**
     * 缩略图素材ID
     */
    @Column(name = "thumb_media_id")
    private String thumbMediaId;

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

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取类型
     *
     * @return type - 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型
     *
     * @param type 类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取图片URL
     *
     * @return url - 图片URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置图片URL
     *
     * @param url 图片URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取素材ID
     *
     * @return media_id - 素材ID
     */
    public String getMediaId() {
        return mediaId;
    }

    /**
     * 设置素材ID
     *
     * @param mediaId 素材ID
     */
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    /**
     * 获取缩略图素材ID
     *
     * @return thumb_media_id - 缩略图素材ID
     */
    public String getThumbMediaId() {
        return thumbMediaId;
    }

    /**
     * 设置缩略图素材ID
     *
     * @param thumbMediaId 缩略图素材ID
     */
    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    /**
     * 获取微信账号ID
     *
     * @return wx_account_id - 微信账号ID
     */
    public String getWxAccountId() {
        return wxAccountId;
    }

    /**
     * 设置微信账号ID
     *
     * @param wxAccountId 微信账号ID
     */
    public void setWxAccountId(String wxAccountId) {
        this.wxAccountId = wxAccountId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}