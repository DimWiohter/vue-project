package com.coffeewx.service.impl;

import com.coffeewx.dao.WxMediaUploadMapper;
import com.coffeewx.model.WxMediaUpload;
import com.coffeewx.service.WxMediaUploadService;
import com.coffeewx.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by CodeGenerator on 2019/03/11.
 */
@Service
@Transactional
public class WxMediaUploadServiceImpl extends AbstractService<WxMediaUpload> implements WxMediaUploadService {
    @Autowired
    private WxMediaUploadMapper tWxMediaUploadMapper;

    @Override
    public List<WxMediaUpload> findList(WxMediaUpload tWxMediaUpload){
        return tWxMediaUploadMapper.findList(tWxMediaUpload);
    }

}
