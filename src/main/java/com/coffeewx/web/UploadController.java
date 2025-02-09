package com.coffeewx.web;

import com.coffeewx.annotation.IgnoreToken;
import com.coffeewx.core.ProjectConstant;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;

/**
 * 七牛上传模块
 *
 * @author Kevin
 */
@RestController
public class UploadController extends AbstractController {

    @Value("${upload.news.dir}")
    private String uploadDirStr;

    @GetMapping("/qiniu/upload/token")
    public Result getToken() {
        String accessKey = "qi5Xf8wubUQ889lX0zdBSivfoCK_3dwvcJtz1cFd";
        String secretKey = "akHi8U_lOcbVJjdFwZBF4hLwU2viSxBqGNOrOqpj";
        String bucket = "coffeewx-web-ui";
        Auth auth = Auth.create( accessKey, secretKey );
        String upToken = auth.uploadToken( bucket );
        return ResultGenerator.genSuccessResult( upToken );
    }

    @RequestMapping("/upload/addImg")
    public Result addImg(@RequestBody MultipartFile file, HttpServletRequest request) throws Exception {
        System.out.println( "上传图片是否为空：" + file.isEmpty() );
        if(file == null){
            return ResultGenerator.genFailResult( "没有找到相对应的文件" );
        }
        String path = null;// 文件路径
        String imgType = null;//图片类型
        String fileName = file.getOriginalFilename();// 原文件名称
        String trueFileName = null;
        // 判断图片类型
        imgType = fileName.indexOf( "." ) != -1 ? fileName.substring( fileName.lastIndexOf( "." ) + 1, fileName.length() ) : null;
        if(imgType == null){
            return ResultGenerator.genFailResult( "文件类型为空" );
        }

        if ("GIF".equals( imgType.toUpperCase() ) || "PNG".equals( imgType.toUpperCase() ) || "JPG".equals( imgType.toUpperCase() )) {
            // 项目在容器中实际发布运行的根路径
            //String realPath = request.getSession().getServletContext().getRealPath( "/" );

            File uploadDir = new File(uploadDirStr);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // 自定义的文件名称
            trueFileName = String.valueOf( System.currentTimeMillis() ) + "_" + fileName;
            // 图片存放的实际路径
            path = uploadDirStr + trueFileName;

            // 转存文件到指定路径
            file.transferTo( new File( path ) ); // 转存而不是写出

            logger.info( "原文件名称 : {}", fileName );
            logger.info( "存储文件名称 : {}", trueFileName );
            logger.info( "存放图片，实际路劲 : {}", path );
        } else {
            return ResultGenerator.genFailResult( "请上传GIF、PNG或者JPG格式的文件" );
        }
        return ResultGenerator.genSuccessResult( path );
    }

    @IgnoreToken
    @RequestMapping(value = "showImage")
    public String showImage(HttpServletRequest request, HttpServletResponse response) {
        String filepath = null;//文件路径
        try {
            filepath = URLDecoder.decode(request.getParameter("filepath"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filepath);
            int i = fileInputStream.available();
            // 得到文件大小
            byte data[] = new byte[i];
            fileInputStream.read(data);
            // 读数据
            response.setContentType("image/*");
            // 设置返回的文件类型
            OutputStream outStream = response.getOutputStream();
            // 得到向客户端输出二进制数据的对象
            outStream.write(data);
            // 输出数据
            outStream.flush();
            outStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


}
