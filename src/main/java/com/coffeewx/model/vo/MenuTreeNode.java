package com.coffeewx.model.vo;

import com.coffeewx.model.WxMenu;
import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单树节点
 * @author Kevin
 * @date 2019-01-22 11:29
 */
@Data
public class MenuTreeNode extends WxMenu implements Serializable {

    List<MenuTreeNode> children = Lists.newArrayList();

}
