package com.coffeewx.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Kevin
 * @date 2019-01-14 18:27
 */
@Data
@AllArgsConstructor
public class TokenReqVO implements Serializable{

    private String token;

}
