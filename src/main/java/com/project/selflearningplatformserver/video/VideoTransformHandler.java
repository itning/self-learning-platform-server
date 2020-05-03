package com.project.selflearningplatformserver.video;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.project.selflearningplatformserver.config.AppProperties;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author itning
 * @date 2020/5/3 12:07
 */
@Slf4j
@Component
public class VideoTransformHandler {
    private final Video2M3u8Helper video2M3u8Helper;
    private final LinkedBlockingQueue<Task> taskLinkedBlockingQueue;
    private final ThreadPoolExecutor transformExecutorService;
    private final ThreadPoolExecutor synchronousBlockingSingleService;
    private final File learningContentTranscodingDir;

    @Autowired
    public VideoTransformHandler(Video2M3u8Helper video2M3u8Helper, AppProperties appProperties) {
        this.video2M3u8Helper = video2M3u8Helper;

        this.taskLinkedBlockingQueue = new LinkedBlockingQueue<>();

        int processors = Runtime.getRuntime().availableProcessors();
        this.transformExecutorService = new ThreadPoolExecutor(processors,
                processors,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                new ThreadFactoryBuilder().setNameFormat("trans-pool-%d").build());
        this.synchronousBlockingSingleService = new ThreadPoolExecutor(1,
                1,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                new ThreadFactoryBuilder().setNameFormat("single-pool-%d").build());
        this.learningContentTranscodingDir = new File(appProperties.getLearningContentTranscodingDir());
        start();
    }

    private void start() {
        synchronousBlockingSingleService.submit(() -> {
            //noinspection InfiniteLoopStatement
            while (true) {
                try {
                    final Task take = taskLinkedBlockingQueue.take();
                    transformExecutorService.submit(() -> video2M3u8Helper.videoConvert(take.getSrc().getPath(), take.getToPath().getPath(), take.getFileName()));
                } catch (Exception e) {
                    log.error("Submit Video Transform Failed {}", e.getMessage());
                }
            }
        });
    }

    public void addTask(File videoFile) throws Exception {
        Task task = new Task(videoFile, learningContentTranscodingDir);
        try {
            taskLinkedBlockingQueue.put(task);
        } catch (Exception e) {
            log.error("Put Task({}) TO Queue Failed {}", task, e.getMessage());
            throw new Exception("转码任务添加失败");
        }
    }

    public boolean isNowTranscoding(File videoFile) {
        return new File(learningContentTranscodingDir + File.separator + DigestUtils.md5DigestAsHex(videoFile.getPath().getBytes()) + ".mp4").exists();
    }

    @ToString
    static class Task {
        private final File src;
        private final File toPath;
        private final String fileName;

        public Task(File src, File toPath) {
            this(src, toPath, src.getName().substring(0, src.getName().lastIndexOf(".")));
        }

        public Task(File src, File toPath, String fileName) {
            this.src = src;
            this.toPath = toPath;
            this.fileName = fileName;
        }

        public File getSrc() {
            return src;
        }

        public File getToPath() {
            return toPath;
        }

        public String getFileName() {
            return fileName;
        }
    }
}
