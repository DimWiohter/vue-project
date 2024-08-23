package com.coffeewx.wxmp.config;

import com.coffeewx.utils.SpringContextUtil;
import com.coffeewx.wxmp.handler.*;
import com.google.common.collect.Maps;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.constant.WxMpEventConstants;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WxMpConfig {

    private static Map<String, WxMpMessageRouter> routers = Maps.newHashMap();
    private static Map<String, WxMpService> mpServices = Maps.newHashMap();

    public static void init(WxMpProperties properties){
        final List<WxMpProperties.MpConfig> configs = properties.getConfigs();
        if (configs == null) {
            throw new RuntimeException("缺少相关配置！");
        }

        mpServices = configs.stream().map(a -> {
            WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
            configStorage.setAppId(a.getAppId());
            configStorage.setSecret(a.getSecret());
            configStorage.setToken(a.getToken());
            configStorage.setAesKey(a.getAesKey());

            WxMpService service = new WxMpServiceImpl();
            service.setWxMpConfigStorage(configStorage);
            routers.put(a.getAppId(), newRouter(service));
            return service;
        }).collect( Collectors.toMap( s -> s.getWxMpConfigStorage().getAppId(), a -> a, (o, n) -> o));

    }

    public static Map<String, WxMpMessageRouter> getRouters() {
        return routers;
    }

    public static Map<String, WxMpService> getMpServices() {
        return mpServices;
    }

    private static WxMpMessageRouter newRouter(WxMpService wxMpService) {
        final WxMpMessageRouter newRouter = new WxMpMessageRouter(wxMpService);

        // 记录所有事件的日志 （异步执行）
        newRouter.rule().handler( SpringContextUtil.getBean( LogHandler.class )).next();

        // 接收客服会话管理事件
        newRouter.rule().async(false).msgType( WxConsts.XmlMsgType.EVENT)
                .event( WxMpEventConstants.CustomerService.KF_CREATE_SESSION)
                .handler(SpringContextUtil.getBean( KfSessionHandler.class )).end();
        newRouter.rule().async(false).msgType( WxConsts.XmlMsgType.EVENT)
                .event(WxMpEventConstants.CustomerService.KF_CLOSE_SESSION)
                .handler(SpringContextUtil.getBean( KfSessionHandler.class ))
                .end();
        newRouter.rule().async(false).msgType( WxConsts.XmlMsgType.EVENT)
                .event(WxMpEventConstants.CustomerService.KF_SWITCH_SESSION)
                .handler(SpringContextUtil.getBean( KfSessionHandler.class )).end();

        // 门店审核事件
        newRouter.rule().async(false).msgType( WxConsts.XmlMsgType.EVENT)
                .event(WxMpEventConstants.POI_CHECK_NOTIFY)
                .handler(SpringContextUtil.getBean( StoreCheckNotifyHandler.class )).end();

        // 自定义菜单事件
        newRouter.rule().async(false).msgType( WxConsts.XmlMsgType.EVENT)
                .event( WxConsts.MenuButtonType.CLICK).handler(SpringContextUtil.getBean( MenuHandler.class )).end();

        // 点击菜单连接事件
        newRouter.rule().async(false).msgType( WxConsts.XmlMsgType.EVENT)
                .event( WxConsts.MenuButtonType.VIEW).handler(SpringContextUtil.getBean( NullHandler.class )).end();

        // 关注事件
        newRouter.rule().async(false).msgType( WxConsts.XmlMsgType.EVENT)
                .event( WxConsts.EventType.SUBSCRIBE).handler(SpringContextUtil.getBean( SubscribeHandler.class ))
                .end();

        // 取消关注事件
        newRouter.rule().async(false).msgType( WxConsts.XmlMsgType.EVENT)
                .event( WxConsts.EventType.UNSUBSCRIBE)
                .handler(SpringContextUtil.getBean( UnsubscribeHandler.class )).end();

        // 上报地理位置事件
        newRouter.rule().async(false).msgType( WxConsts.XmlMsgType.EVENT)
                .event( WxConsts.EventType.LOCATION).handler(SpringContextUtil.getBean( LocationHandler.class ))
                .end();

        // 接收地理位置消息
        newRouter.rule().async(false).msgType( WxConsts.XmlMsgType.LOCATION)
                .handler(SpringContextUtil.getBean( LocationHandler.class )).end();

        // 扫码事件
        newRouter.rule().async(false).msgType( WxConsts.XmlMsgType.EVENT)
                .event( WxConsts.EventType.SCAN).handler(SpringContextUtil.getBean( ScanHandler.class )).end();

        // 默认
        newRouter.rule().async(false).handler(SpringContextUtil.getBean( MsgHandler.class )).end();

        return newRouter;
    }

}
