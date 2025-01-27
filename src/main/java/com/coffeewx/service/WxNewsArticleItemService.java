package com.coffeewx.service;
import com.coffeewx.model.WxNewsArticleItem;
import com.coffeewx.core.Service;
import java.util.List;

/**
 * Created by CodeGenerator on 2019/03/11.
 */
public interface WxNewsArticleItemService extends Service<WxNewsArticleItem> {

    List<WxNewsArticleItem> findList(WxNewsArticleItem tWxNewsArticleItem);

}
