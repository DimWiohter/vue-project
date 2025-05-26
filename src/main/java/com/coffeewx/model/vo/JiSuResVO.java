package com.coffeewx.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Kevin
 * @date 2019-03-21 15:03
 */
@Data
public class JiSuResVO implements Serializable{
        private String status;
        private String msg;
        private Object result;

        public boolean isSuccess(){
            return this.status.equals( "0" );
        }

}
