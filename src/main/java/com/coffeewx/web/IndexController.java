package com.coffeewx.web;

import com.coffeewx.annotation.IgnoreToken;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.coffeewx.model.vo.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 首页
 *
 * @author Kevin
 * @date 2019-03-27 16:42
 */
@RestController
@IgnoreToken
public class IndexController extends AbstractController {

    @Value("${spring.application.name}")
    private String applicationName;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append( "<html>" )
                .append( "<body>" )
                .append( "<h2 style=\"margin: 50px 0 0 80px;\">" )
                .append( "欢迎访问【" + applicationName + "】" )
                .append( "</h2>" )
                .append( "</body>" )
                .append( "</html>" );
        response.setHeader( "content-type", "text/html;charset=UTF-8" );
        response.getWriter().write( builder.toString() );
    }

    @RequestMapping(value = "/monitor/server", method = RequestMethod.GET)
    public Result server() throws Exception {
        Server server = new Server();
        try {
            server.copyTo();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultGenerator.genSuccessResult( server );
    }

}
