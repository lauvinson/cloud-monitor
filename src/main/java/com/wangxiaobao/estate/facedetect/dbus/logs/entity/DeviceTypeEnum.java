package com.wangxiaobao.estate.facedetect.dbus.logs.entity;

/**
 * 设备类型
 * @author created by vinson on 2019/8/5
 */
public enum DeviceTypeEnum {
    NVR("NVR"),
    CAMERA("抓拍机"),
    WITNESS_CARD_MACHINE("一体机"),
    SERVER("服务器");
    private String deviceType;
    DeviceTypeEnum(String type) {
      this.deviceType=type;
    }
}
