package com.wangxiaobao.estate.facedetect.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 请求文件属性
 * @author created by vinson on 2019/8/5
 */
@Data
@AllArgsConstructor
public class RequestFile {
    private String argument;
    private List<RequestFileElement> files;

    @Data
    @AllArgsConstructor
    public static class RequestFileElement {
        private String name;
        private Long size;
    }

}
