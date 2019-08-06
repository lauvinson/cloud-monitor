package com.wangxiaobao.estate.facedetect.dbus.logs;

import com.wangxiaobao.estate.facedetect.common.RestResponse;
import com.wangxiaobao.estate.facedetect.dbus.logs.entity.DeviceLogsEntity;
import com.wangxiaobao.estate.facedetect.dbus.logs.entity.ServiceLogsEntity;
import com.wangxiaobao.estate.facedetect.dbus.logs.service.TransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/face-detect/dbus/local-monitor/log")
@RestController
public class Controller {

    @Autowired
    private TransferService transferService;

    @RestResponse
    @GetMapping("device")
    public Object device(String id) {
        return transferService.findByDeviceLogId(id);
    }

    @RestResponse
    @PostMapping("device")
    public DeviceLogsEntity device(DeviceLogsEntity deviceLogsEntity) {
        return transferService.saveDeviceLog(deviceLogsEntity);
    }

    @RestResponse
    @PostMapping("service")
    public ServiceLogsEntity service(ServiceLogsEntity serviceLogsEntity) {
        return transferService.saveServiceLog(serviceLogsEntity);
    }
}
