package com.wangxiaobao.estate.facedetect.dbus.logs.service;


import com.wangxiaobao.estate.facedetect.dbus.logs.entity.DeviceLogsEntity;
import com.wangxiaobao.estate.facedetect.dbus.logs.entity.ServiceLogsEntity;
import com.wangxiaobao.estate.facedetect.dbus.logs.repo.DeviceLogRepository;
import com.wangxiaobao.estate.facedetect.dbus.logs.repo.ServiceLogRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 数据转接服务
 * @author created by vinson on 2019/8/5
 */
@org.springframework.stereotype.Service
public class TransferService {

    @Autowired
    private DeviceLogRepository deviceLogRepository;

    @Autowired
    private ServiceLogRepository serviceLogRepository;

    public DeviceLogsEntity saveDeviceLog(DeviceLogsEntity deviceLogsEntity) {
        return deviceLogRepository.save(deviceLogsEntity);
    }

    public ServiceLogsEntity saveServiceLog(ServiceLogsEntity serviceLogsEntity) {
        return serviceLogRepository.save(serviceLogsEntity);
    }

    public DeviceLogsEntity findByDeviceLogId(String deviceLogId) {
        return deviceLogRepository.findById(deviceLogId).get();
    }

}
