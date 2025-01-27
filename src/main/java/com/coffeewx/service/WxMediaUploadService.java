package com.coffeewx.service;
import com.coffeewx.model.WxMediaUpload;
import com.coffeewx.core.Service;
import java.util.List;

/**
 * Created by CodeGenerator on 2019/03/11.
 */
public interface WxMediaUploadService extends Service<WxMediaUpload> {

    List<WxMediaUpload> findList(WxMediaUpload tWxMediaUpload);

}
