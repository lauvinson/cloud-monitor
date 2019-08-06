package com.wangxiaobao.estate.facedetect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * GO!GO!GO!
 * @author created by vinson on 2019/8/5
 */
@SpringBootApplication
@EnableElasticsearchRepositories
public class AppLauncher {
    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(AppLauncher.class, args);
    }
}
