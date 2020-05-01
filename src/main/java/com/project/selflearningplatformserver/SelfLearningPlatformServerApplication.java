package com.project.selflearningplatformserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author itning
 */
@SpringBootApplication
@MapperScan("com.project.selflearningplatformserver.mapper")
public class SelfLearningPlatformServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SelfLearningPlatformServerApplication.class, args);
    }

}
