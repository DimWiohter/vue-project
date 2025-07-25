package com.coffeewx.model.vo;

import lombok.Data;

/**权限-按钮资源
 * @author Kevin
 * @date 2019-03-28 18:07
 */
@Data
public class ButtonVO {

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
     * 资源图标
     */
    private String icon;

}
