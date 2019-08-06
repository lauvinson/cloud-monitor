package com.wangxiaobao.estate.facedetect.dbus.logs.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 设备信息
 * @author created by vinson on 2019/8/5
 */
@Data
public class DeviceInfoEntity implements Serializable{
    /**
     * 设备Id
     */
    private String deviceId;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 设备类型
     */
    private DeviceTypeEnum deviceTypeEnum;
    /**
     * Ip地址
     */
    private String ipAddress;

    /**
     * 安装地点
     */
//    @JSONField(serialize = false)
    private String installationLocation;
}
