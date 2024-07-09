package com.coffeewx.dao;

import com.coffeewx.core.Mapper;
import com.coffeewx.model.WxMenu;

import java.util.List;

public interface WxMenuMapper extends Mapper<WxMenu> {

    List<WxMenu> findList(WxMenu tWxMenu);

    List<WxMenu> listTreeMenu(WxMenu tWxMenu);

}