package com.wangxiaobao.estate.facedetect.common;

/**
 * 响应状态消息
 * @author created by vinson on 2019/8/5
 */
public enum ResponseStatusMessage {
    /** 成功 **/
    success("0"),
    /** 失败 **/
    fail("-1"),
    /** 异常 **/
    exception("400");
    /** 状态码 **/
    String code;

    ResponseStatusMessage(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
