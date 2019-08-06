package com.wangxiaobao.estate.facedetect.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 响应内容载体
 * @author created by vinson on 2019/8/5
 */
@Data
public class ResponseMessage<T> implements Serializable {
    private String code;
    private String action;
    private String msg;
    private T data;

    public ResponseMessage(String action, T data) {
        super();
        this.code = ResponseStatusMessage.success.getCode();
        this.msg = ResponseStatusMessage.success.name();
        this.data = data;
        this.action = action;
    }

    public ResponseMessage(String code, String action, String msg, T data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.action = action;
    }

    private ResponseMessage(String code, String action, String msg) {
        super();
        this.code = code;
        this.msg = msg;
        this.action = action;
    }

    public static ResponseMessage fail(String action, String msg) {
        return new ResponseMessage(ResponseStatusMessage.success.getCode(), action, msg);
    }

    public static ResponseMessage exception(String code, String action, String msg) {
        return new ResponseMessage(code, action, msg);
    }
}
