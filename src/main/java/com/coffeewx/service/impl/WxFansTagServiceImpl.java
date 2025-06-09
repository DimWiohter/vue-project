package com.coffeewx.service.impl;

import cn.hutool.core.date.DateUtil;
import com.coffeewx.core.AbstractService;
import com.coffeewx.dao.WxFansTagMapper;
import com.coffeewx.model.WxAccount;
import com.coffeewx.model.WxFansTag;
import com.coffeewx.service.WxAccountService;
import com.coffeewx.service.WxFansTagService;
import com.coffeewx.wxmp.config.WxMpConfig;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by CodeGenerator on 2019/03/21.
 */
@Service
@Transactional
public class WxFansTagServiceImpl extends AbstractService<WxFansTag> implements WxFansTagService {
    @Autowired
    private WxFansTagMapper tWxFansTagMapper;

    @Autowired
    private WxAccountService wxAccountService;

    @Override
    public List<WxFansTag> findList(WxFansTag tWxFansTag){
        return tWxFansTagMapper.findList(tWxFansTag);
    }

    @Override
    public void syncFansTag(WxAccount wxAccount) throws Exception {
        wxAccount = wxAccountService.findById( wxAccount.getId() );
        WxMpService wxMpService = WxMpConfig.getMpServices().get( wxAccount.getAppid() );
        List<WxUserTag> wxUserTagList = wxMpService.getUserTagService().tagGet();

        WxFansTag wxFansTagTpl = new WxFansTag();
        wxFansTagTpl.setWxAccountId( String.valueOf( wxAccount.getId() ) );
        List<WxFansTag> wxFansTagList = this.findList( wxFansTagTpl );
        wxFansTagList.forEach( temp ->{
            this.deleteById( temp.getId() );
        });

        for (int i = 0; i < wxUserTagList.size(); i++) {
            WxFansTag wxFansTag = new WxFansTag();
            wxFansTag.setId( wxUserTagList.get( i ).getId().intValue() );
            wxFansTag.setWxAccountId( String.valueOf( wxAccount.getId() ) );
            wxFansTag.setCount( wxUserTagList.get( i ).getCount() );
            wxFansTag.setName( wxUserTagList.get( i ).getName() );
            wxFansTag.setCreateTime( DateUtil.date() );
            wxFansTag.setUpdateTime( DateUtil.date() );
            this.save( wxFansTag );
        }
    }

}
