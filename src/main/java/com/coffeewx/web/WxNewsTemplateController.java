package com.coffeewx.web;

import cn.hutool.core.date.DateUtil;
import com.coffeewx.core.ProjectConstant;
import com.coffeewx.core.Result;
import com.coffeewx.core.ResultGenerator;
import com.coffeewx.model.WxNewsArticleItem;
import com.coffeewx.model.WxNewsTemplate;
import com.coffeewx.model.vo.NewsTemplateVO;
import com.coffeewx.service.WxNewsArticleItemService;
import com.coffeewx.service.WxNewsTemplateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by CodeGenerator on 2019/03/11.
 */
@RestController
@RequestMapping("/wx/news/template")
public class WxNewsTemplateController extends AbstractController {
    @Autowired
    private WxNewsTemplateService wxNewsTemplateService;

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
    public Result addNews(@RequestBody NewsTemplateVO newsTemplateVO) throws Exception{
        wxNewsTemplateService.addNews( newsTemplateVO );
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/uploadNews")
    public Result uploadNews(@RequestBody NewsTemplateVO newsTemplateVO) throws Exception{
        wxNewsTemplateService.uploadNews( newsTemplateVO );
        return ResultGenerator.genSuccessResult();
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

    /**
     * 查询某个公众号的图文并且已经上传的，并且是单个图文的
     *
     * @param wxAccountId
     * @return com.coffeewx.core.Result
     * @author Kevin
     * @date 2019-03-14 18:46:42
     */
    @PostMapping("/listAll2")
    public Result listAll2(@RequestParam String wxAccountId) {
        List <WxNewsTemplate> list = Lists.newArrayList();
        List <WxNewsTemplate> filterList = Lists.newArrayList();
        if (StringUtils.isNotBlank( wxAccountId )) {
            WxNewsTemplate wxNewsTemplate = new WxNewsTemplate();
            wxNewsTemplate.setWxAccountId( wxAccountId );
            wxNewsTemplate.setIsUpload( "1" );
            list = wxNewsTemplateService.findList( wxNewsTemplate );
            filterList = list.stream().filter(temp -> temp.getCountArticle() == 1).collect( Collectors.toList());
        }
        return ResultGenerator.genSuccessResult( filterList );
    }


}
