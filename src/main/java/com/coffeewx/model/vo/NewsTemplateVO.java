package com.coffeewx.model.vo;

import com.coffeewx.model.WxNewsArticleItem;
import com.coffeewx.model.WxNewsTemplate;
import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 图文模型
 * @author Kevin
 * @date 2019-03-11 16:04
 */
@Data
public class NewsTemplateVO extends WxNewsTemplate implements Serializable{

    private List<String> deleteArticleIds = Lists.newArrayList();

    private List<WxNewsArticleItem> list = Lists.newArrayList();

}
