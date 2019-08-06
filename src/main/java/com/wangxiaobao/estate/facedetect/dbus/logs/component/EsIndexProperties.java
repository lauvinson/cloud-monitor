package com.wangxiaobao.estate.facedetect.dbus.logs.component;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class EsIndexProperties {

    @Value("${spring.data.elasticsearch.deviceLogIndex}")
    private String deviceLogIndex;

    @Value("${spring.data.elasticsearch.deviceLogType}")
    private String deviceLogType;

    @Value("${spring.data.elasticsearch.serviceLogIndex}")
    private String serviceLogIndex;

    @Value("${spring.data.elasticsearch.serviceLogType}")
    private String serviceLogType;
}
