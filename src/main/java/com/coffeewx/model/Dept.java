package com.coffeewx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_dept")
public class Dept {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门等级
     */
    private Integer level;

    /**
     * 部门排序
     */
    @Column(name = "order_no")
    private Integer orderNo;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 上级部门
     */
    @Column(name = "parent_id")
    private String parentId;

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
     * 获取部门名称
     *
     * @return name - 部门名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置部门名称
     *
     * @param name 部门名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取部门等级
     *
     * @return level - 部门等级
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置部门等级
     *
     * @param level 部门等级
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取部门排序
     *
     * @return order_no - 部门排序
     */
    public Integer getOrderNo() {
        return orderNo;
    }

    /**
     * 设置部门排序
     *
     * @param orderNo 部门排序
     */
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
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
     * 获取上级部门
     *
     * @return parent_id - 上级部门
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置上级部门
     *
     * @param parentId 上级部门
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
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