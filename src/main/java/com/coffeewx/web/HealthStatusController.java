package com.coffeewx.web;

import com.coffeewx.annotation.IgnoreToken;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:接口状态检查
 * @Author:Kevin
 * @Date:2018/8/9 20:44
 */
@RestController
@RequestMapping("/health")
public class HealthStatusController {

    /**
     * 检查接口状态
     *
     * @return
     */
    @IgnoreToken
    @GetMapping("status")
    public Result status() {
        return ResultGenerator.genSuccessResult( HttpStatus.OK );
    }
    
    /**
     * 检查header中token是否过期
     * @param 
     * @return com.springboot.core.Result
     * @author Kevin
     * @date 2018-12-11 18:50:51
     */
    @PostMapping("/checkToken")
    public Result checkToken() {
        return ResultGenerator.genSuccessResult( null);
    }

}
