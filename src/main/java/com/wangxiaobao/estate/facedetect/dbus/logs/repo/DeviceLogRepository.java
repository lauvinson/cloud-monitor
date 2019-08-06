package com.wangxiaobao.estate.facedetect.dbus.logs.repo;

import com.wangxiaobao.estate.facedetect.dbus.logs.entity.DeviceLogsEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceLogRepository extends ElasticsearchRepository<DeviceLogsEntity, String> {
}
