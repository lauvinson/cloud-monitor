package com.wangxiaobao.estate.facedetect.dbus.logs.entity;

/**
 * 服务状态
 * @author created by vinson on 2019/8/5
 */
public enum ServiceStateEnum {
    NOR("正常"),
    ERROR("异常错误"),
    UNKNOWN("状态未知");
    private String serviceState;
    ServiceStateEnum(String type) {
       this.serviceState=type;
    }
}
