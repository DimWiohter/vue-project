package com.coffeewx.service.impl;

import cn.hutool.core.date.DateUtil;
import com.coffeewx.core.AbstractService;
import com.coffeewx.dao.WxAccountMapper;
import com.coffeewx.model.WxAccount;
import com.coffeewx.service.WxAccountService;
import com.coffeewx.wxmp.config.WxMpConfig;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2019/01/16.
 */
@Service
@Transactional
public class WxAccountServiceImpl extends AbstractService<WxAccount> implements WxAccountService {
    @Resource
    private WxAccountMapper tWxAccountMapper;

    @Override
    public List<WxAccount> findList(WxAccount wxAccount) {
        return tWxAccountMapper.findList( wxAccount );
    }

    @Override
    public void generateQRUrl(WxAccount wxAccount) throws Exception{
        wxAccount = this.findById( wxAccount.getId() );
        WxMpService wxMpService = WxMpConfig.getMpServices().get( wxAccount.getAppid() );
        WxMpQrCodeTicket ticket = wxMpService.getQrcodeService().qrCodeCreateLastTicket( 1 );
        String url = wxMpService.getQrcodeService().qrCodePictureUrl(ticket.getTicket());

        wxAccount.setQrUrl( url );
        wxAccount.setUpdateTime( DateUtil.date() );
        this.update( wxAccount );
    }
}
