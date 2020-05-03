package com.project.selflearningplatformserver.service.impl;

import com.project.selflearningplatformserver.config.AppProperties;
import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.entity.LearningContent;
import com.project.selflearningplatformserver.entity.Subject;
import com.project.selflearningplatformserver.exception.IdNotFoundException;
import com.project.selflearningplatformserver.exception.NullFiledException;
import com.project.selflearningplatformserver.exception.SecurityServerException;
import com.project.selflearningplatformserver.mapper.LearningContentMapper;
import com.project.selflearningplatformserver.mapper.SubjectMapper;
import com.project.selflearningplatformserver.service.LearningContentService;
import com.project.selflearningplatformserver.video.VideoTransformHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author itning
 * @date 2020/5/1 20:46
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service
public class LearningContentServiceImpl implements LearningContentService {
    private final LearningContentMapper learningContentMapper;
    private final SubjectMapper subjectMapper;
    private final AppProperties appProperties;
    private final VideoTransformHandler videoTransformHandler;

    @Autowired
    public LearningContentServiceImpl(LearningContentMapper learningContentMapper, SubjectMapper subjectMapper, AppProperties appProperties, VideoTransformHandler videoTransformHandler) {
        this.learningContentMapper = learningContentMapper;
        this.subjectMapper = subjectMapper;
        this.appProperties = appProperties;
        this.videoTransformHandler = videoTransformHandler;
    }

    @Override
    public List<LearningContent> getAllBySubjectId(String subjectId) {
        return learningContentMapper.selectAllBySubjectId(subjectId);
    }

    @Override
    public void delLearningContent(LoginUser loginUser, String id) {
        LearningContent learningContent = learningContentMapper.selectByPrimaryKey(id);
        if (Objects.isNull(learningContent)) {
            throw new IdNotFoundException("学习内容ID不存在");
        }
        Subject subject = subjectMapper.selectByPrimaryKey(learningContent.getSubjectId());
        if (!loginUser.getId().equals(subject.getUserId())) {
            throw new SecurityServerException("删除失败", HttpStatus.FORBIDDEN);
        }
        if (videoTransformHandler.isNowTranscoding(new File(appProperties.getLearningContentDir() + learningContent.getContentUri()))) {
            throw new SecurityServerException("文件正在转码，无法删除，请稍后再试", HttpStatus.BAD_REQUEST);
        }
        delFiles(learningContent);
        learningContentMapper.deleteByPrimaryKey(id);
    }

    /**
     * 删除学习内容所上传的文件
     *
     * @param learningContent 学习内容
     */
    private void delFiles(LearningContent learningContent) {
        File file = new File(appProperties.getLearningContentDir() + learningContent.getContentUri());
        File[] files = new File(appProperties.getLearningContentTranscodingDir()).listFiles();
        if (Objects.nonNull(files)) {
            Arrays.stream(files).filter(f -> f.getName().startsWith(learningContent.getId())).forEach(f -> {
                boolean isDelete = f.delete();
                if (log.isDebugEnabled()) {
                    log.debug("File {} Delete {}", f, isDelete ? "Success" : "Failed");
                }
            });
        }
        if (file.exists()) {
            boolean isDelete = file.delete();
            if (log.isDebugEnabled()) {
                log.debug("File {} Delete {}", file, isDelete ? "Success" : "Failed");
            }
        } else {
            if (log.isDebugEnabled()) {
                log.debug("File {} Not Found", file);
            }
        }
    }

    @Override
    public LearningContent newLearningContent(LoginUser loginUser, MultipartFile file, String subjectId, String name) {
        if (StringUtils.isAnyBlank(subjectId, name)) {
            throw new NullFiledException("参数为空");
        }
        Subject subject = subjectMapper.selectByPrimaryKey(subjectId);
        if (Objects.isNull(subject)) {
            throw new IdNotFoundException("科目ID不存在");
        }
        if (!subject.getUserId().equals(loginUser.getId())) {
            throw new SecurityServerException("新增失败", HttpStatus.FORBIDDEN);
        }
        String id = UUID.randomUUID().toString();
        String filenameExtension = org.springframework.util.StringUtils.getFilenameExtension(file.getOriginalFilename());
        String newFileName = id + "." + filenameExtension;
        try {
            File saveFile = new File(appProperties.getLearningContentDir() + newFileName);
            file.transferTo(saveFile);
            Date date = new Date();
            LearningContent learningContent = new LearningContent();
            learningContent.setId(id);
            learningContent.setSubjectId(subjectId);
            learningContent.setContentUri(newFileName);
            learningContent.setExtensionName(filenameExtension);
            learningContent.setSize(file.getSize());
            learningContent.setMime(file.getContentType());
            learningContent.setName(name);
            learningContent.setGmtCreate(date);
            learningContent.setGmtModified(date);
            learningContentMapper.insert(learningContent);
            videoTransformHandler.addTask(saveFile);
            return learningContent;
        } catch (IOException e) {
            if (log.isDebugEnabled()) {
                log.debug("Upload Exception {}", e.getMessage());
            }
            throw new SecurityServerException("上传错误", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new SecurityServerException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public LearningContent updateLearningContent(LoginUser loginUser, String id, String name) {
        if (StringUtils.isAnyBlank(id, name)) {
            throw new NullFiledException("参数为空");
        }
        if (learningContentMapper.countByPrimaryKey(id) == 0L) {
            throw new IdNotFoundException("学习内容ID不存在");
        }
        LearningContent learningContent = new LearningContent();
        learningContent.setGmtModified(new Date());
        learningContent.setId(id);
        learningContent.setName(name);
        learningContentMapper.updateByPrimaryKeySelective(learningContent);
        return learningContent;
    }

    @Override
    public List<LearningContent> getAllCanLearningContent(LoginUser loginUser) {
        return learningContentMapper.selectAllCanStudy(loginUser.getId());
    }
}
