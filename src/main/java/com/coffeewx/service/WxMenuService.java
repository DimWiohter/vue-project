package com.coffeewx.service;
import com.coffeewx.model.WxMenu;
import com.coffeewx.core.Service;
import com.coffeewx.model.vo.MenuTreeNode;

import java.util.List;

/**
 * Created by CodeGenerator on 2019/01/17.
 */
public interface WxMenuService extends Service<WxMenu> {

    List<WxMenu> findList(WxMenu tWxMenu);

    List<WxMenu> listTreeMenu();

    List<WxMenu> listTreeMenu(String wxAccountId);

    List<MenuTreeNode> convertTreeMenu(List<WxMenu> list);

}
