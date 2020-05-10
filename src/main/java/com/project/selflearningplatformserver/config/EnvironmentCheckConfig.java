package com.project.selflearningplatformserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author itning
 * @date 2020/5/10 15:54
 */
@Slf4j
@Component
public class EnvironmentCheckConfig implements EnvironmentAware {

    @Override
    public void setEnvironment(@NonNull Environment environment) {
        checkDirExist(environment.getRequiredProperty("app.learning-content-dir", File.class));
        checkDirExist(environment.getRequiredProperty("app.learning-content-aid-dir", File.class));
        checkDirExist(environment.getRequiredProperty("app.learning-content-transcoding-dir", File.class));
        checkDirExist(environment.getRequiredProperty("app.student-work-dir", File.class));
        checkDirExist(environment.getRequiredProperty("app.ffmpeg-bin-dir", File.class));
    }

    private void checkDirExist(File dir) {
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                if (log.isInfoEnabled()) {
                    log.info("Created Dir From Path {}", dir);
                }
                checkDirExist(dir);
            } else {
                throw new RuntimeException("Create Dir Failed And Path " + dir);
            }
        } else {
            if (!dir.isDirectory()) {
                throw new RuntimeException("Path " + dir + " Not Dir");
            }
        }
    }
}
