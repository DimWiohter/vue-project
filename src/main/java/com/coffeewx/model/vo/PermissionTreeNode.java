package com.coffeewx.model.vo;

import com.coffeewx.model.Permission;
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
public class PermissionTreeNode extends Permission implements Serializable {

    List<PermissionTreeNode> children = Lists.newArrayList();

}
