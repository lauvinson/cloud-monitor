package com.wangxiaobao.estate.facedetect.dbus.logs.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * 服务日志
 * @author created by vinson on 2019/8/5
 */
@Data
@Document(indexName = "#{esIndexProperties.serviceLogIndex}", type = "#{esIndexProperties.serviceLogType}")
public class ServiceLogsEntity implements Serializable {
    /**
     * 主键UUID，按时间顺序生成
     */
    @Id
    private String serviceLogId;
    /**
     * 集团Id
     */
    @Field(type = FieldType.Text)
    private String groupId;
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
     * 服务名称
     */
    @Field(type = FieldType.Text)
    private String serviceName;
    /**
     * 服务状态
     */
    @Field(type = FieldType.Text)
    private ServiceStateEnum serviceStateEnum;
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
