package com.wangxiaobao.estate.facedetect.dbus.logs.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * 设备日志
 * @author created by vinson on 2019/8/5
 */
@Data
@Document(indexName = "#{esIndexProperties.deviceLogIndex}", type = "#{esIndexProperties.deviceLogType}")
public class DeviceLogsEntity implements Serializable {

    /**
     * 主键UUID，按时间顺序生成
     */
    @Id
    private String deviceLogId;
    /**
     * 集团Id
     */
    @Field(type = FieldType.Text)
    private String groupId;
    /**
     * 集团名称
     **/
    @Field(type = FieldType.Text)
    private String groupName;
    /**
     * 項目Id
     */
    @Field(type = FieldType.Text)
    private String projectId;
    /**
     * 案场Id
     **/
    @Field(type = FieldType.Text)
    private String fieldId;
    /**
     * 设备Id
     */
    @Field(type = FieldType.Text)
    private String deviceId;
    /**
     * 设备名称
     */
    @Field(type = FieldType.Text)
    private String deviceName;
    /**
     * 设备类型
     */
    @Field(type = FieldType.Text)
    private DeviceTypeEnum deviceTypeEnum;
    /**
     * Ip地址
     */
    @Field(type = FieldType.Text)
    private String ipAddress;

    /**
     * 安装地点
     */
    @Field(type = FieldType.Text)
    private String installationLocation;
    /**
     * 设备信息
     */
    @Field(type = FieldType.Text)
    private DeviceInfoEntity deviceInfoEntity;
    /**
     * 设备状态
     */
    @Field(type = FieldType.Text)
    private DeviceStateEnum deviceStateEnum;
    /**
     * 首次更新时间
     */
    @Field(type = FieldType.Text)
    private String firstUpdateTime;
    /**
     * 最新更新时间
     */
    @Field(type = FieldType.Text)
    private String lastUpdateTime;
    /**
     * 日志创建时间
     */
    @Field(type = FieldType.Text)
    private String createDate;

}
