package com.coffeewx.model.vo;

import com.coffeewx.model.WxAccount;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * 微信账号树节点
 * @author Kevin
 * @date 2019-04-01 12:20
 */
public class WxAccountTreeNode extends WxAccount implements Serializable {

    List<WxAccountTreeNode> children = Lists.newArrayList();

}
