package com.wangxiaobao.estate.facedetect.common;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ResponseBodyAnalysis implements ResponseBodyAdvice {

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        AnnotatedElement annotatedElement = methodParameter.getAnnotatedElement();
        RestResponse restResponse = AnnotationUtils.findAnnotation(annotatedElement, RestResponse.class);
        return restResponse != null;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public ResponseMessage beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        HttpServletRequest request = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
        Map<String, String[]> params = request.getParameterMap();
        if (MapUtils.isNotEmpty(params)) {
            log.info("[{}] {} to {} from {}, params {}", request.getSession().getId(), request.getMethod(), request.getRequestURI(), serverHttpRequest.getRemoteAddress(), JSONObject.toJSONString(params));
        }
        if (null != request.getContentType() && request.getContentType().startsWith(MediaType.MULTIPART_FORM_DATA_VALUE)) {
            MultiValueMap<String, MultipartFile> files = ((StandardMultipartHttpServletRequest) request).getMultiFileMap();
            List<RequestFile> fAttr = new ArrayList<>();
            files.forEach((k,v) -> {
                List<RequestFile.RequestFileElement> rfe = new ArrayList<>();
                for (MultipartFile multipartFile : v) {
                    rfe.add(new RequestFile.RequestFileElement(multipartFile.getOriginalFilename(), multipartFile.getSize()));
                }
                fAttr.add(new RequestFile(k, rfe));
            });
            if (CollectionUtils.isNotEmpty(fAttr)) {
                log.info("[{}] {} to {} from {}, files {}", request.getSession().getId(), request.getMethod(),  request.getRequestURI(), serverHttpRequest.getRemoteAddress(), JSONObject.toJSONString(fAttr));
            }
        }
        serverHttpResponse.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        return new ResponseMessage<>(serverHttpRequest.getURI().getPath(), o);
    }
}
