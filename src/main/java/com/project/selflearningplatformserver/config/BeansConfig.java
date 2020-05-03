package com.project.selflearningplatformserver.config;

import com.project.selflearningplatformserver.video.Video2M3u8Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * @author itning
 * @date 2020/5/3 12:05
 */
@Configuration
public class BeansConfig {
    private final AppProperties appProperties;

    @Autowired
    public BeansConfig(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(System.getProperty("java.io.tmpdir"));
        return factory.createMultipartConfig();
    }

    @Bean
    public Video2M3u8Helper video2M3u8Helper() {
        return new Video2M3u8Helper(appProperties.getFfmpegBinDir());
    }
}
