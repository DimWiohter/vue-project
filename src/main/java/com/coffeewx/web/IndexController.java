package com.coffeewx.web;

import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页
 * @author Kevin
 * @date 2019-03-27 16:42
 */
@RestController
public class IndexController extends AbstractController{

    @Value("${spring.application.name}")
    private String applicationName;

    @RequestMapping("/")
    public Result index() {

        return ResultGenerator.genSuccessResult( "欢迎访问" + "【" + applicationName + "】");
    }

}
