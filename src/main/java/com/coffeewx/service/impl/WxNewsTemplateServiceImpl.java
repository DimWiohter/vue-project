package com.coffeewx.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.coffeewx.core.AbstractService;
import com.coffeewx.core.ProjectConstant;
import com.coffeewx.dao.WxNewsTemplateMapper;
import com.coffeewx.model.WxAccount;
import com.coffeewx.model.WxAccountFans;
import com.coffeewx.model.WxNewsArticleItem;
import com.coffeewx.model.WxNewsTemplate;
import com.coffeewx.model.vo.NewsPreviewVO;
import com.coffeewx.model.vo.NewsTemplateVO;
import com.coffeewx.service.WxAccountFansService;
import com.coffeewx.service.WxAccountService;
import com.coffeewx.service.WxNewsArticleItemService;
import com.coffeewx.service.WxNewsTemplateService;
import com.coffeewx.utils.URLUtils;
import com.coffeewx.wxmp.config.WxMpConfig;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpMassPreviewMessage;
import me.chanjar.weixin.mp.bean.material.WxMediaImgUploadResult;
import me.chanjar.weixin.mp.bean.material.WxMpMaterial;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialUploadResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

/**
 * Created by CodeGenerator on 2019/03/11.
 */
@Service
@Transactional
public class WxNewsTemplateServiceImpl extends AbstractService<WxNewsTemplate> implements WxNewsTemplateService {

    @Autowired
    private WxNewsTemplateMapper tWxNewsTemplateMapper;

    @Autowired
    private WxAccountService wxAccountService;

    @Autowired
    private WxAccountFansService wxAccountFansService;

    @Autowired
    private WxNewsArticleItemService wxNewsArticleItemService;

    @Value("${upload.news.dir}")
    private String uploadDirStr;

    @Override
    public List<WxNewsTemplate> findList(WxNewsTemplate tWxNewsTemplate){
        return tWxNewsTemplateMapper.findList(tWxNewsTemplate);
    }

    @Override
    public void addNews(NewsTemplateVO newsTemplateVO) throws Exception {
        WxNewsTemplate wxNewsTemplate = this.findById( newsTemplateVO.getId() );

        //处理待删除的article item
        if (newsTemplateVO.getDeleteArticleIds().size() > 0) {
            wxNewsArticleItemService.deleteByIds( StringUtils.join( newsTemplateVO.getDeleteArticleIds().toArray(), "," ) );
        }

        //处理相关字段
        for (int i = 0; i < newsTemplateVO.getList().size(); i++) {
            WxNewsArticleItem wxNewsArticleItem = newsTemplateVO.getList().get( i );

            wxNewsArticleItem.setShowCoverPic( ProjectConstant.NO );
            wxNewsArticleItem.setOrderNo( i );
            wxNewsArticleItem.setNewsId( String.valueOf( newsTemplateVO.getId() ) );
            wxNewsArticleItem.setWxAccountId( wxNewsTemplate.getWxAccountId() );

            if (wxNewsArticleItem.getId() == null) {
                wxNewsArticleItem.setCreateTime( DateUtil.date() );
                wxNewsArticleItem.setUpdateTime( DateUtil.date() );
                wxNewsArticleItemService.save( wxNewsArticleItem );
            } else {

                WxNewsArticleItem wxNewsArticleItem2 = wxNewsArticleItemService.findById( wxNewsArticleItem.getId() );
                //如果图片地址发生变化,ThumbMediaId重置，上传微信的时候，根据该字段，判断是否需要上传
                if (!wxNewsArticleItem.getPicPath().equals( wxNewsArticleItem2.getPicPath() )) {
                    wxNewsArticleItem.setThumbMediaId( "" );
                }
                wxNewsArticleItem.setUpdateTime( DateUtil.date() );
                wxNewsArticleItemService.update( wxNewsArticleItem );
            }
        }
    }

    @Override
    public void uploadNews(NewsTemplateVO newsTemplateVO) throws Exception {
        WxNewsTemplate wxNewsTemplate = this.findById( newsTemplateVO.getId() );

        //处理待删除的article item
        if (newsTemplateVO.getDeleteArticleIds().size() > 0) {
            wxNewsArticleItemService.deleteByIds( StringUtils.join( newsTemplateVO.getDeleteArticleIds().toArray(), "," ) );
        }

        //获取公众号service
        WxAccount wxAccount = wxAccountService.findById( Integer.parseInt( wxNewsTemplate.getWxAccountId() ) );
        WxMpService wxMpService = WxMpConfig.getMpServices().get( wxAccount.getAppid() );
        WxMpMaterialNews wxMpMaterialNews = new WxMpMaterialNews();

        //处理相关字段
        for (int i = 0; i < newsTemplateVO.getList().size(); i++) {
            WxNewsArticleItem wxNewsArticleItem = newsTemplateVO.getList().get( i );

            wxNewsArticleItem.setShowCoverPic( ProjectConstant.NO );
            wxNewsArticleItem.setOrderNo( i );
            wxNewsArticleItem.setNewsId( String.valueOf( newsTemplateVO.getId() ) );
            wxNewsArticleItem.setWxAccountId( wxNewsTemplate.getWxAccountId() );

            WxMpMaterialNews.WxMpMaterialNewsArticle article = new WxMpMaterialNews.WxMpMaterialNewsArticle();
            if (wxNewsArticleItem.getId() == null) {
                WxMpMaterialUploadResult wxMpMaterialUploadResult = uploadPhotoToWx( wxMpService, wxNewsArticleItem.getPicPath() );
                wxNewsArticleItem.setThumbMediaId( wxMpMaterialUploadResult.getMediaId() );
                wxNewsArticleItem.setCreateTime( DateUtil.date() );
                wxNewsArticleItem.setUpdateTime( DateUtil.date() );

                article.setAuthor( wxNewsArticleItem.getAuthor() );

                //处理content
                String content = processContent(wxMpService, wxNewsArticleItem.getContent());
                article.setContent( content );
                article.setContentSourceUrl( wxNewsArticleItem.getContentSourceUrl() );
                article.setDigest( wxNewsArticleItem.getDigest() );
                article.setShowCoverPic( wxNewsArticleItem.getShowCoverPic().equals( "1" ) ? true : false );
                article.setThumbMediaId( wxNewsArticleItem.getThumbMediaId() );
                article.setTitle( wxNewsArticleItem.getTitle() );
                //TODO 暂时注释掉，测试号没有留言权限
                //article.setNeedOpenComment( wxNewsArticleItem.getNeedOpenComment().equals( "1" ) ? true : false );
                //article.setOnlyFansCanComment( wxNewsArticleItem.getOnlyFansCanComment().equals( "1" ) ? true : false );
                wxMpMaterialNews.addArticle( article );

                wxNewsArticleItemService.save( wxNewsArticleItem );
            } else {

                WxNewsArticleItem wxNewsArticleItem2 = wxNewsArticleItemService.findById( wxNewsArticleItem.getId() );
                //如果图片地址发生变化,ThumbMediaId重置，上传微信的时候，根据该字段，判断是否需要上传
                if (!wxNewsArticleItem.getPicPath().equals( wxNewsArticleItem2.getPicPath() ) || StringUtils.isBlank( wxNewsArticleItem.getThumbMediaId() )) {
                    WxMpMaterialUploadResult wxMpMaterialUploadResult = uploadPhotoToWx( wxMpService, wxNewsArticleItem.getPicPath() );
                    wxNewsArticleItem.setThumbMediaId( wxMpMaterialUploadResult.getMediaId() );

                }
                wxNewsArticleItem.setUpdateTime( DateUtil.date() );
                wxNewsArticleItemService.update( wxNewsArticleItem );

                article.setAuthor( wxNewsArticleItem.getAuthor() );

                //处理content
                String content = processContent(wxMpService, wxNewsArticleItem.getContent());
                article.setContent( content );

                article.setContentSourceUrl( wxNewsArticleItem.getContentSourceUrl() );
                article.setDigest( wxNewsArticleItem.getDigest() );
                article.setShowCoverPic( wxNewsArticleItem.getShowCoverPic().equals( "1" ) ? true : false );
                article.setThumbMediaId( wxNewsArticleItem.getThumbMediaId() );
                article.setTitle( wxNewsArticleItem.getTitle() );
                //TODO 暂时注释掉，测试号没有留言权限
                //article.setNeedOpenComment( wxNewsArticleItem.getNeedOpenComment().equals( "1" ) ? true : false );
                //article.setOnlyFansCanComment( wxNewsArticleItem.getOnlyFansCanComment().equals( "1" ) ? true : false );
                wxMpMaterialNews.addArticle( article );

            }

        }

        logger.info( "wxMpMaterialNews : {}", JSONUtil.toJsonStr( wxMpMaterialNews ) );

        WxMpMaterialUploadResult wxMpMaterialUploadResult = wxMpService.getMaterialService().materialNewsUpload( wxMpMaterialNews );
        logger.info( "wxMpMaterialUploadResult : {}", JSONUtil.toJsonStr( wxMpMaterialUploadResult ) );
        wxNewsTemplate.setIsUpload( ProjectConstant.YES );
        wxNewsTemplate.setMediaId( wxMpMaterialUploadResult.getMediaId() );
        wxNewsTemplate.setUpdateTime( DateUtil.date() );
        this.update( wxNewsTemplate );
    }

    @Override
    public void sendNewsPreview(NewsPreviewVO newsPreviewVO) throws Exception {
        //获取公众号service
        WxAccount wxAccount = wxAccountService.findById( Integer.parseInt( newsPreviewVO.getWxAccountId() ) );
        WxMpService wxMpService = WxMpConfig.getMpServices().get( wxAccount.getAppid() );

        WxNewsTemplate wxNewsTemplate = this.findById( Integer.valueOf( newsPreviewVO.getNewsId() ) );

        for (int i = 0; i < newsPreviewVO.getFansSelected().size(); i++) {
            WxAccountFans wxAccountFans = wxAccountFansService.findById( Integer.valueOf( newsPreviewVO.getFansSelected().get( i ) ) );
            WxMpMassPreviewMessage wxMpMassPreviewMessage = new WxMpMassPreviewMessage();
            wxMpMassPreviewMessage.setMsgType( WxConsts.MassMsgType.MPNEWS );
            wxMpMassPreviewMessage.setMediaId( wxNewsTemplate.getMediaId() );
            wxMpMassPreviewMessage.setToWxUserOpenid( wxAccountFans.getOpenid() );
            wxMpService.getMassMessageService().massMessagePreview( wxMpMassPreviewMessage );
        }
        
    }

    private WxMpMaterialUploadResult uploadPhotoToWx(WxMpService wxMpService, String picPath) throws WxErrorException {
        WxMpMaterial wxMpMaterial = new WxMpMaterial();
        File picFile = new File( picPath );
        wxMpMaterial.setFile( picFile );
        wxMpMaterial.setName( picFile.getName() );
        logger.info( "picFile name : {}", picFile.getName() );
        WxMpMaterialUploadResult wxMpMaterialUploadResult = wxMpService.getMaterialService().materialFileUpload( WxConsts.MediaFileType.IMAGE, wxMpMaterial );
        logger.info( "wxMpMaterialUploadResult : {}", JSONUtil.toJsonStr( wxMpMaterialUploadResult ) );
        return wxMpMaterialUploadResult;
    }

    private String processContent(WxMpService wxMpService,String content) throws WxErrorException{
        if(StringUtils.isBlank( content )){
            return content;
        }
        String imgReg = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
        List<String> imgList = ReUtil.findAllGroup1( imgReg,content);
        for (int j = 0; j < imgList.size(); j++) {
            String imgSrc = imgList.get( j );
            String filepath = URLUtils.getParam( imgSrc,"filepath" );

            if(StringUtils.isBlank( filepath )){//网络图片URL，需下载到本地
                String filename = String.valueOf( System.currentTimeMillis() ) + ".png";
                String downloadPath = uploadDirStr + filename;
                long size = HttpUtil.downloadFile(imgSrc, FileUtil.file(downloadPath));
                filepath = downloadPath;
            }
            WxMediaImgUploadResult wxMediaImgUploadResult = wxMpService.getMaterialService().mediaImgUpload( new File(filepath) );
            content = StringUtils.replace( content,imgList.get( j ),wxMediaImgUploadResult.getUrl());
        }
        return content;
    }


}
