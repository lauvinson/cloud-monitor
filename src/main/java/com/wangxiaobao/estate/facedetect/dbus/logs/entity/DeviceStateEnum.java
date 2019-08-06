package com.wangxiaobao.estate.facedetect.dbus.logs.entity;

/**
 * 设备状态
 * @author created by vinson on 2019/8/5
 */
public enum DeviceStateEnum {
    offLine("不在线"),
    onLine("在线");
    private String deviceState;
    DeviceStateEnum(String type) {
      this.deviceState=type;
    }
}
