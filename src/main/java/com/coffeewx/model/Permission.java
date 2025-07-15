package com.coffeewx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_permission")
public class Permission {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 上级资源ID
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 资源编码
     */
    private String resources;

    /**
     * 资源名称
     */
    private String title;

    /**
     * 资源级别
     */
    private Integer level;

    /**
     * 资源图标
     */
    private String icon;

    /**
     * 类型 menu、button
     */
    private String type;

    /**
     * 备注
     */
    private String remarks;

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
     * 获取上级资源ID
     *
     * @return parent_id - 上级资源ID
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置上级资源ID
     *
     * @param parentId 上级资源ID
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取资源编码
     *
     * @return resources - 资源编码
     */
    public String getResources() {
        return resources;
    }

    /**
     * 设置资源编码
     *
     * @param resources 资源编码
     */
    public void setResources(String resources) {
        this.resources = resources;
    }

    /**
     * 获取资源名称
     *
     * @return title - 资源名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置资源名称
     *
     * @param title 资源名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取资源级别
     *
     * @return level - 资源级别
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置资源级别
     *
     * @param level 资源级别
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取资源图标
     *
     * @return icon - 资源图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置资源图标
     *
     * @param icon 资源图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取类型 menu、button
     *
     * @return type - 类型 menu、button
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型 menu、button
     *
     * @param type 类型 menu、button
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取备注
     *
     * @return remarks - 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
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