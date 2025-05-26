package com.coffeewx.model.vo;

import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Kevin
 * @date 2019-03-20 18:54
 */
@Data
public class NewsPreviewVO implements Serializable {
    private String newsId;
    private String wxAccountId;
    private List<String> fansSelected = Lists.newArrayList();
}
