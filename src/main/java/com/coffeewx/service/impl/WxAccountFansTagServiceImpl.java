package com.coffeewx.service.impl;

import cn.hutool.core.date.DateUtil;
import com.coffeewx.core.AbstractService;
import com.coffeewx.dao.WxAccountFansTagMapper;
import com.coffeewx.model.WxAccount;
import com.coffeewx.model.WxAccountFansTag;
import com.coffeewx.service.WxAccountFansTagService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by CodeGenerator on 2019/03/21.
 */
@Service
@Transactional
public class WxAccountFansTagServiceImpl extends AbstractService<WxAccountFansTag> implements WxAccountFansTagService {
    @Autowired
    private WxAccountFansTagMapper tWxAccountFansTagMapper;

    @Override
    public List<WxAccountFansTag> findList(WxAccountFansTag tWxAccountFansTag){
        return tWxAccountFansTagMapper.findList(tWxAccountFansTag);
    }

    public void processFansTags(WxAccount wxAccount,WxMpUser wxmpUser){
        WxAccountFansTag wxAccountFansTagTpl = new WxAccountFansTag();
        wxAccountFansTagTpl.setOpenid( wxmpUser.getOpenId() );
        List<WxAccountFansTag> wxAccountFansTagList = this.findList( wxAccountFansTagTpl );
        wxAccountFansTagList.forEach( temp ->{
            this.deleteById( temp.getId() );
        } );

        Long[] tagIds = wxmpUser.getTagIds();
        for (Long tagId : tagIds) {
            WxAccountFansTag wxAccountFansTag = new WxAccountFansTag();
            wxAccountFansTag.setOpenid( wxmpUser.getOpenId() );
            wxAccountFansTag.setTagId( String.valueOf( tagId ) );
            wxAccountFansTag.setWxAccountId( String.valueOf( wxAccount.getId() ) );
            wxAccountFansTag.setCreateTime( DateUtil.date() );
            wxAccountFansTag.setUpdateTime( DateUtil.date() );
            this.save( wxAccountFansTag );
        }
    }

}
