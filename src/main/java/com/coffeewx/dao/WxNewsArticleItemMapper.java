package com.coffeewx.dao;

import com.coffeewx.core.Mapper;
import com.coffeewx.model.WxNewsArticleItem;

import java.util.List;

public interface WxNewsArticleItemMapper extends Mapper<WxNewsArticleItem> {

    List<WxNewsArticleItem> findList(WxNewsArticleItem wxNewsArticleItem);

}