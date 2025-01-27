package com.coffeewx;

import com.coffeewx.service.WxAccountService;
import com.coffeewx.wxmp.config.WxMpConfig;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpUserService;
import me.chanjar.weixin.mp.bean.WxMpMassNews;
import me.chanjar.weixin.mp.bean.WxMpMassOpenIdsMessage;
import me.chanjar.weixin.mp.bean.material.WxMediaImgUploadResult;
import me.chanjar.weixin.mp.bean.result.WxMpMassSendResult;
import me.chanjar.weixin.mp.bean.result.WxMpMassUploadResult;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * TODO
 *
 * @author Kevin
 * @date 2019-03-06 19:14
 */
public class TestNewsMsg extends Tester{

    @Autowired
    private WxAccountService wxAccountService;

    @org.junit.Test
    public void testNews(){
//        WxMpService wxMpService = WxMpConfig.getMpServices().get( "1231231" );
//        WxMpUserService wxMpUserService = wxMpService.getUserService();
//
//
//        // 上传图文消息的封面图片
//        WxMediaUploadResult uploadMediaRes = wxMpService.getMaterialService().mediaUpload( WxConsts.MEDIA_IMAGE, "jpg", inputStream);
//
//// 上传图文消息的正文图片(返回的url拼在正文的<img>标签中)
//        WxMediaImgUploadResult imagedMediaRes = wxMpService.getMaterialService().mediaImgUpload(file);
//        String url=imagedMediaRes.getUrl();
//
//        WxMpMassNews news = new WxMpMassNews();
//        WxMpMassNews.WxMpMassNewsArticle article1 = new WxMpMassNews.WxMpMassNewsArticle();
//        article1.setTitle("标题1");
//        article1.setContent("内容1");
//        article1.setThumbMediaId(uploadMediaRes.getMediaId());
//        news.addArticle(article1);
//
//        WxMpMassNews.WxMpMassNewsArticle article2 = new WxMpMassNews.WxMpMassNewsArticle();
//        article2.setTitle("标题2");
//        article2.setContent("内容2");
//        article2.setThumbMediaId(uploadMediaRes.getMediaId());
//        article2.setShowCoverPic(true);
//        article2.setAuthor("作者2");
//        article2.setContentSourceUrl("www.baidu.com");
//        article2.setDigest("摘要2");
//        news.addArticle(article2);
//
//
//        WxMpMassUploadResult massUploadResult = wxMpService.getMassMessageService().massNewsUpload(news);
//
//        WxMpMassOpenIdsMessage massMessage = new WxMpMassOpenIdsMessage();
//        massMessage.setMsgType(WxConsts.MASS_MSG_NEWS);
//        massMessage.setMediaId(uploadResult.getMediaId());
//        massMessage.getToUsers().add(openid);
//
//        WxMpMassSendResult massResult = wxMpService.getMassMessageService().massOpenIdsMessageSend(massMessage);

    }

}
