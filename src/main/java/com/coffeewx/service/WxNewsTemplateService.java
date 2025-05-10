package com.coffeewx.service;

import com.coffeewx.core.Service;
import com.coffeewx.model.WxNewsTemplate;
import com.coffeewx.model.vo.NewsTemplateVO;

import java.util.List;

/**
 * Created by CodeGenerator on 2019/03/11.
 */
public interface WxNewsTemplateService extends Service<WxNewsTemplate> {

    List<WxNewsTemplate> findList(WxNewsTemplate tWxNewsTemplate);

    void addNews(NewsTemplateVO newsTemplateVO) throws Exception;

    void uploadNews(NewsTemplateVO newsTemplateVO) throws Exception;

}
