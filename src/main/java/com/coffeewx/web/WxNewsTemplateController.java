package com.coffeewx.web;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.coffeewx.core.ProjectConstant;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.coffeewx.model.WxAccount;
import com.coffeewx.model.WxNewsArticleItem;
import com.coffeewx.model.WxNewsTemplate;
import com.coffeewx.model.vo.NewsTemplateVO;
import com.coffeewx.service.WxAccountService;
import com.coffeewx.service.WxNewsArticleItemService;
import com.coffeewx.service.WxNewsTemplateService;
import com.coffeewx.wxmp.config.WxMpConfig;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.material.WxMpMaterial;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialUploadResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by CodeGenerator on 2019/03/11.
 */
@RestController
@RequestMapping("/wx/news/template")
public class WxNewsTemplateController extends AbstractController {
    @Autowired
    private WxNewsTemplateService wxNewsTemplateService;

    @Autowired
    private WxAccountService wxAccountService;

    @Autowired
    private WxNewsArticleItemService wxNewsArticleItemService;

    @PostMapping("/add")
    public Result add(@RequestBody WxNewsTemplate wxNewsTemplate) {
        wxNewsTemplate.setIsUpload( ProjectConstant.NO );
        wxNewsTemplate.setCreateTime( DateUtil.date() );
        wxNewsTemplate.setUpdateTime( DateUtil.date() );
        wxNewsTemplateService.save( wxNewsTemplate );
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        //删除关联的article item
        WxNewsArticleItem wxNewsArticleItem = new WxNewsArticleItem();
        wxNewsArticleItem.setNewsId( String.valueOf( id ) );
        List <WxNewsArticleItem> wxNewsArticleItemList = wxNewsArticleItemService.findList( wxNewsArticleItem );
        for (int i = 0; i < wxNewsArticleItemList.size(); i++) {
            wxNewsArticleItemService.deleteById( wxNewsArticleItemList.get( i ).getId() );
        }

        wxNewsTemplateService.deleteById( id );
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody WxNewsTemplate wxNewsTemplate) {
        wxNewsTemplate.setUpdateTime( DateUtil.date() );
        wxNewsTemplateService.update( wxNewsTemplate );
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        WxNewsTemplate wxNewsTemplate = wxNewsTemplateService.findById( id );
        return ResultGenerator.genSuccessResult( wxNewsTemplate );
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer limit, @RequestParam String tplName, @RequestParam String wxAccountId) {
        PageHelper.startPage( page, limit );
        WxNewsTemplate wxNewsTemplate = new WxNewsTemplate();
        wxNewsTemplate.setTplName( tplName );
        wxNewsTemplate.setWxAccountId( wxAccountId );
        List <WxNewsTemplate> list = wxNewsTemplateService.findList( wxNewsTemplate );
        PageInfo pageInfo = new PageInfo( list );
        return ResultGenerator.genSuccessResult( pageInfo );
    }

    @PostMapping("/getNews")
    public Result getNews(@RequestParam Integer id) {
        WxNewsArticleItem wxNewsArticleItem = new WxNewsArticleItem();
        wxNewsArticleItem.setNewsId( String.valueOf( id ) );
        List <WxNewsArticleItem> list = wxNewsArticleItemService.findList( wxNewsArticleItem );
        //orderNo asc
        Collections.sort( list, Comparator.comparing( WxNewsArticleItem::getOrderNo ) );
        return ResultGenerator.genSuccessResult( list );
    }

    @PostMapping("/addNews")
    public Result addNews(@RequestBody NewsTemplateVO newsTemplateVO) {
        WxNewsTemplate wxNewsTemplate = wxNewsTemplateService.findById( newsTemplateVO.getId() );

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
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/uploadNews")
    public Result uploadNews(@RequestBody NewsTemplateVO newsTemplateVO) {
        WxNewsTemplate wxNewsTemplate = wxNewsTemplateService.findById( newsTemplateVO.getId() );

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
                WxMpMaterialUploadResult wxMpMaterialUploadResult = null;
                try {
                    wxMpMaterialUploadResult = uploadPhotoToWx( wxMpService, wxNewsArticleItem.getPicPath() );
                } catch (WxErrorException e) {
                    e.printStackTrace();
                    return ResultGenerator.genFailResult( e.getMessage() );
                }
                wxNewsArticleItem.setThumbMediaId( wxMpMaterialUploadResult.getMediaId() );
                wxNewsArticleItem.setCreateTime( DateUtil.date() );
                wxNewsArticleItem.setUpdateTime( DateUtil.date() );

                article.setAuthor( wxNewsArticleItem.getAuthor() );
                article.setContent( wxNewsArticleItem.getContent() );
                article.setContentSourceUrl( wxNewsArticleItem.getContentSourceUrl() );
                article.setDigest( wxNewsArticleItem.getDigest() );
                article.setShowCoverPic( wxNewsArticleItem.getShowCoverPic().equals( "1" ) ? true : false );
                article.setThumbMediaId( wxNewsArticleItem.getThumbMediaId() );
                article.setTitle( wxNewsArticleItem.getTitle() );
                article.setNeedOpenComment( wxNewsArticleItem.getNeedOpenComment().equals( "1" ) ? true : false );
                article.setOnlyFansCanComment( wxNewsArticleItem.getOnlyFansCanComment().equals( "1" ) ? true : false );
                wxMpMaterialNews.addArticle( article );

                wxNewsArticleItemService.save( wxNewsArticleItem );
            } else {

                WxNewsArticleItem wxNewsArticleItem2 = wxNewsArticleItemService.findById( wxNewsArticleItem.getId() );
                //如果图片地址发生变化,ThumbMediaId重置，上传微信的时候，根据该字段，判断是否需要上传
                if (!wxNewsArticleItem.getPicPath().equals( wxNewsArticleItem2.getPicPath() ) || StringUtils.isBlank( wxNewsArticleItem.getThumbMediaId() )) {
                    WxMpMaterialUploadResult wxMpMaterialUploadResult = null;
                    try {
                        wxMpMaterialUploadResult = uploadPhotoToWx( wxMpService, wxNewsArticleItem.getPicPath() );
                    } catch (WxErrorException e) {
                        e.printStackTrace();
                        return ResultGenerator.genFailResult( e.getMessage() );
                    }
                    wxNewsArticleItem.setThumbMediaId( wxMpMaterialUploadResult.getMediaId() );

                }
                wxNewsArticleItem.setUpdateTime( DateUtil.date() );
                wxNewsArticleItemService.update( wxNewsArticleItem );

                article.setAuthor( wxNewsArticleItem.getAuthor() );
                article.setContent( wxNewsArticleItem.getContent() );
                article.setContentSourceUrl( wxNewsArticleItem.getContentSourceUrl() );
                article.setDigest( wxNewsArticleItem.getDigest() );
                article.setShowCoverPic( wxNewsArticleItem.getShowCoverPic().equals( "1" ) ? true : false );
                article.setThumbMediaId( wxNewsArticleItem.getThumbMediaId() );
                article.setTitle( wxNewsArticleItem.getTitle() );
                article.setNeedOpenComment( wxNewsArticleItem.getNeedOpenComment().equals( "1" ) ? true : false );
                article.setOnlyFansCanComment( wxNewsArticleItem.getOnlyFansCanComment().equals( "1" ) ? true : false );
                wxMpMaterialNews.addArticle( article );

            }

        }

        logger.info( "wxMpMaterialNews : {}", JSONUtil.toJsonStr( wxMpMaterialNews ) );
        try {
            WxMpMaterialUploadResult wxMpMaterialUploadResult = wxMpService.getMaterialService().materialNewsUpload( wxMpMaterialNews );
            logger.info( "wxMpMaterialUploadResult : {}", JSONUtil.toJsonStr( wxMpMaterialUploadResult ) );
            wxNewsTemplate.setIsUpload( ProjectConstant.YES );
            wxNewsTemplate.setMediaId( wxMpMaterialUploadResult.getMediaId() );
            wxNewsTemplate.setUpdateTime( DateUtil.date() );
            wxNewsTemplateService.update( wxNewsTemplate );
        } catch (WxErrorException e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult( e.getMessage() );
        }

        return ResultGenerator.genSuccessResult();

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

    /**
     * 查询某个公众号的图文并且已经上传的
     *
     * @param wxAccountId
     * @return com.coffeewx.core.Result
     * @author Kevin
     * @date 2019-03-14 18:46:42
     */
    @PostMapping("/listAll")
    public Result listAll(@RequestParam String wxAccountId) {
        List <WxNewsTemplate> list = Lists.newArrayList();
        if (StringUtils.isNotBlank( wxAccountId )) {
            WxNewsTemplate wxNewsTemplate = new WxNewsTemplate();
            wxNewsTemplate.setWxAccountId( wxAccountId );
            wxNewsTemplate.setIsUpload( "1" );
            list = wxNewsTemplateService.findList( wxNewsTemplate );
        }
        return ResultGenerator.genSuccessResult( list );
    }

}
