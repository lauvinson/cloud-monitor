package com.wangxiaobao.estate.facedetect.common;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class ResponseMessageWebConfigure implements WebMvcConfigurer {

    @Bean
    public HttpMessageConverters converters() {
        HttpMessageConverter converter = new FastJsonHttpMessageConverter();
        return new HttpMessageConverters(converter);
    }

}
