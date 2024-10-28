package com.coffeewx.wxmp.config;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public class WxMpProperties {

    private static volatile WxMpProperties wxMpProperties;

    private WxMpProperties() {}

    public static WxMpProperties getInstance() {
        if (wxMpProperties == null) {
            synchronized (WxMpProperties.class) {
                if (wxMpProperties == null) {
                    wxMpProperties = new WxMpProperties();
                }
            }
        }
        return wxMpProperties;
    }

    private List<MpConfig> configs = Lists.newArrayList();

    @Data
    public static class MpConfig {
        /**
         * 设置微信公众号的appid
         */
        private String appId;

        /**
         * 设置微信公众号的app secret
         */
        private String secret;

        /**
         * 设置微信公众号的token
         */
        private String token;

        /**
         * 设置微信公众号的EncodingAESKey
         */
        private String aesKey;
    }
}
