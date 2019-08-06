package com.wangxiaobao.estate.facedetect.dbus.logs.repo;

import com.wangxiaobao.estate.facedetect.dbus.logs.entity.ServiceLogsEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceLogRepository extends ElasticsearchRepository<ServiceLogsEntity, String> {
}
