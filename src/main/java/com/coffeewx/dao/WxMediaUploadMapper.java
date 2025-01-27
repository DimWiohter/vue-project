package com.coffeewx.dao;

import com.coffeewx.core.Mapper;
import com.coffeewx.model.WxMediaUpload;

import java.util.List;

public interface WxMediaUploadMapper extends Mapper<WxMediaUpload> {

    List<WxMediaUpload> findList(WxMediaUpload wxMediaUpload);

}