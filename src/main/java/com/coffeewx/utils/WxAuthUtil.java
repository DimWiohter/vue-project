package com.coffeewx.utils;

import me.chanjar.weixin.common.util.http.URIUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * 微信授权工具类
 * @author Kevin
 * @date 2019-01-25 14:32
 */
public class WxAuthUtil {

    //应用授权作用域
    //snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），
    //snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
    public final static String SNSAPI_BASE = "snsapi_base";
    public final static String SNSAPI_USERINFO = "snsapi_userinfo";

    public static String oauth2buildAuthorizationUrl(String appId,String redirectURI, String scope) {
        return oauth2buildAuthorizationUrl(appId,redirectURI,scope,"state");
  }

    private static String oauth2buildAuthorizationUrl(String appId,String redirectURI, String scope, String state) {
        return String.format("https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s&connect_redirect=1#wechat_redirect", new Object[]{appId, URIUtil.encodeURIComponent(redirectURI), scope, StringUtils.trimToEmpty(state)});
    }

    public static void main(String[] args) {
        String appId = "wxff7bf2c34c65e260";//本人微信测试号
        String targetUrl = "http://coffee-ease.natapp1.cc/vue-mobile-seed/index";
        String url = "http://coffee-ease.natapp1.cc/vue-mobile-seed/wxAuth?targetUrl=" + targetUrl;
        String oauth2Url = oauth2buildAuthorizationUrl( appId ,url, SNSAPI_BASE);
        System.out.println(oauth2Url);
    }

}
