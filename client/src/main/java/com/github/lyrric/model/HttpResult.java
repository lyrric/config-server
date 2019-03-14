package com.github.lyrric.model;

import lombok.Data;

/**
 * 响应封装
 */
@Data
public class HttpResult {

    /**
     * 成功代码
     */
    private final static int SUCCESS_CODE = 0;
    /**
     * 业务异常
     */
    private final static int BUSINESS_EXCEPTION_CODE = 1;
    /**
     * 未知异常
     */
    private final static int UNKNOWN_EXCEPTION_CODE = 2;
    /**
     * 成功
     */
    private final static boolean SUCCESS_STATUS = true;
    /**
     * 失败
     */
    private final static boolean FAILURE_STATUS = false;

    /**
     * 响应码。
     * 如果没有特别注明，code为0时表示请求访问成功
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String errMsg;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 响应数据
     */
    private Object data;

    private HttpResult(boolean success, int code, String errMsg, Object data) {
        this.success = success;
        this.code = code;
        this.errMsg = errMsg;
        this.data = data;
    }




    /**
     * 响应成功
     * @param success 是否成功
     * @param code 响应代码
     * @param msg 响应消息
     * @param data 响应数据
     * @return 响应对象
     */
    public static HttpResult success(boolean success, int code, String msg, Object data) {
        return new HttpResult(success, code, msg,data);
    }

    /**
     * 默认成功响应
     * @param data 数据
     * @return 响应
     */
    public static HttpResult success(Object data) {
        return success(SUCCESS_STATUS, SUCCESS_CODE, "请求成功",data);
    }

    /**
     * 响应请求失败
     * @param code 约定code
     * @param msg  信息
     * @return 失败的响应
     */
    public static HttpResult failure(boolean success, int code, String msg, Object data) {
        return new HttpResult(success, code, msg,data);
    }

    /**
     * 响应请求失败
     * @param errMsg 失败信息
     * @return 响应
     */
    public static HttpResult failure(String errMsg) {
        return failure(FAILURE_STATUS, BUSINESS_EXCEPTION_CODE, errMsg,null);
    }
    /**
     * 出现预料之外的错误
     * @return 错误信息
     */
    public static HttpResult error() {
        return failure(FAILURE_STATUS, UNKNOWN_EXCEPTION_CODE, "系统错误",null);
    }

}
