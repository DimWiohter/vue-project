package com.coffeewx.model.vo;

import com.coffeewx.model.User;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * 用户树节点
 * @author Kevin
 * @date 2019-04-01 12:20
 */
public class UserTreeNode extends User implements Serializable {

    List<UserTreeNode> children = Lists.newArrayList();

}
