package com.coffeewx.model.vo;

import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/** 权限-菜单资源
 * @author Kevin
 * @date 2019-03-28 18:06
 */
@Data
public class MenuVO implements Serializable {

    private Integer id;

    /**
     * 上级资源ID
     */
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

    List<MenuVO> children = Lists.newArrayList();

}
