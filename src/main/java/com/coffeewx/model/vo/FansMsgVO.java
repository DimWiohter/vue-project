package com.coffeewx.model.vo;

import lombok.Data;

/**
 * 粉丝消息
 * @author Kevin
 * @date 2019-03-15 13:38
 */
@Data
public class FansMsgVO {

    private String fansId;
    private String msgType;
    private String content;
    private String tplId;

}
