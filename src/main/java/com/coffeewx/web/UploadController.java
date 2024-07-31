package com.coffeewx.web;

import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.qiniu.util.Auth;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 七牛上传模块
 * @author Kevin
 */
@RestController
public class UploadController extends AbstractController{

    @GetMapping("/qiniu/upload/token")
    public Result getToken() {
        String accessKey = "qi5Xf8wubUQ889lX0zdBSivfoCK_3dwvcJtz1cFd";
        String secretKey = "akHi8U_lOcbVJjdFwZBF4hLwU2viSxBqGNOrOqpj";
        String bucket = "coffeewx-web-ui";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        return ResultGenerator.genSuccessResult( upToken );
    }

}
