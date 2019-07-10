package com.github.lyrric.config.server.model;

/**
 * 自定义业务异常
 */
public class BusinessException extends Exception {
    public BusinessException(String msg){
        super(msg);
    }
}
