package com.project.selflearningplatformserver.config;

import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author itning
 * @date 2020/5/3 11:20
 */
@ToString
@Getter
@Component
@ConfigurationProperties("app")
public class AppProperties {
    /**
     * 学习内容文件存放目录
     */
    private String learningContentDir;

    /**
     * 学习内容额外文件存放目录
     */
    private String learningContentAidDir;

    /**
     * 学习内容文件转码后存放的目录
     */
    private String learningContentTranscodingDir;

    /**
     * 学生作业文件存放目录
     */
    private String studentWorkDir;

    /**
     * FFmpeg Bin 目录
     */
    private String ffmpegBinDir;

    public void setLearningContentDir(String learningContentDir) {
        this.learningContentDir = addDirSeparator(learningContentDir);
    }

    public void setLearningContentTranscodingDir(String learningContentTranscodingDir) {
        this.learningContentTranscodingDir = addDirSeparator(learningContentTranscodingDir);
    }

    public void setFfmpegBinDir(String ffmpegBinDir) {
        this.ffmpegBinDir = addDirSeparator(ffmpegBinDir);
    }

    public void setStudentWorkDir(String studentWorkDir) {
        this.studentWorkDir = addDirSeparator(studentWorkDir);
    }

    public void setLearningContentAidDir(String learningContentAidDir) {
        this.learningContentAidDir = addDirSeparator(learningContentAidDir);
    }

    /**
     * 添加斜杠
     *
     * @param dirPath 目录路径
     * @return 目录路径
     * @see File#separator
     */
    private String addDirSeparator(String dirPath) {
        if (!dirPath.endsWith(File.separator)) {
            dirPath += File.separator;
        }
        return dirPath;
    }
}
