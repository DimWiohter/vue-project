package com.coffeewx.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;
@Data
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

    private Integer sortNo;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss",iso= DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "create_time")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss",iso= DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "update_time")
    private Date updateTime;


}