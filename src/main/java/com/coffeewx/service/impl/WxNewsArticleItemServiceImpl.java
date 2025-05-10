package com.coffeewx.service.impl;

import com.coffeewx.core.AbstractService;
import com.coffeewx.dao.WxNewsArticleItemMapper;
import com.coffeewx.model.WxNewsArticleItem;
import com.coffeewx.service.WxNewsArticleItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by CodeGenerator on 2019/03/11.
 */
@Service
@Transactional
public class WxNewsArticleItemServiceImpl extends AbstractService<WxNewsArticleItem> implements WxNewsArticleItemService {
    @Autowired
    private WxNewsArticleItemMapper tWxNewsArticleItemMapper;

    @Override
    public List<WxNewsArticleItem> findList(WxNewsArticleItem tWxNewsArticleItem){
        return tWxNewsArticleItemMapper.findList(tWxNewsArticleItem);
    }

}
