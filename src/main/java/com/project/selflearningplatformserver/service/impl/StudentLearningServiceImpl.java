package com.project.selflearningplatformserver.service.impl;

import com.project.selflearningplatformserver.config.AppProperties;
import com.project.selflearningplatformserver.dto.LearningContentDTO;
import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.dto.StudentLearningDTO;
import com.project.selflearningplatformserver.entity.StudentLearning;
import com.project.selflearningplatformserver.exception.IdNotFoundException;
import com.project.selflearningplatformserver.exception.NullFiledException;
import com.project.selflearningplatformserver.mapper.LearningContentMapper;
import com.project.selflearningplatformserver.mapper.StudentLearningMapper;
import com.project.selflearningplatformserver.mapper.StudentWorkMapper;
import com.project.selflearningplatformserver.service.StudentLearningService;
import com.project.selflearningplatformserver.video.VideoTransformHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author itning
 * @date 2020/5/2 11:29
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class StudentLearningServiceImpl implements StudentLearningService {
    private final StudentLearningMapper studentLearningMapper;
    private final LearningContentMapper learningContentMapper;
    private final StudentWorkMapper studentWorkMapper;
    private final VideoTransformHandler videoTransformHandler;
    private final AppProperties appProperties;

    @Autowired
    public StudentLearningServiceImpl(StudentLearningMapper studentLearningMapper, LearningContentMapper learningContentMapper, StudentWorkMapper studentWorkMapper, VideoTransformHandler videoTransformHandler, AppProperties appProperties) {
        this.studentLearningMapper = studentLearningMapper;
        this.learningContentMapper = learningContentMapper;
        this.studentWorkMapper = studentWorkMapper;
        this.videoTransformHandler = videoTransformHandler;
        this.appProperties = appProperties;
    }

    @Override
    public StudentLearning switchLearningContent(LoginUser loginUser, String learningContentId) {
        if (StringUtils.isBlank(learningContentId)) {
            throw new NullFiledException("学习内容ID为空");
        }
        if (learningContentMapper.countByPrimaryKey(learningContentId) == 0L) {
            throw new IdNotFoundException("学习内容ID不存在");
        }
        Date date = new Date();
        StudentLearning studentLearning = new StudentLearning();
        studentLearning.setId(UUID.randomUUID().toString());
        studentLearning.setLearningContentId(learningContentId);
        studentLearning.setStudentId(loginUser.getId());
        studentLearning.setGmtCreate(date);
        studentLearning.setGmtModified(date);
        studentLearningMapper.insert(studentLearning);
        return studentLearning;
    }

    @Override
    public List<LearningContentDTO> getMyLearning(LoginUser loginUser) {
        return studentLearningMapper.selectAllByStudentId(loginUser.getId())
                .parallelStream()
                .filter(learningContent -> !videoTransformHandler.isNowTranscoding(new File(appProperties.getLearningContentDir() + learningContent.getContentUri())))
                .collect(Collectors.toList());
    }

    @Override
    public void delMyLearning(LoginUser loginUser, String studentLearningId) {
        if (StringUtils.isBlank(studentLearningId)) {
            throw new NullFiledException("学生学习ID为空");
        }
        if (studentLearningMapper.countByPrimaryKey(studentLearningId) == 0L) {
            throw new IdNotFoundException("学生学习不存在");
        }
        studentLearningMapper.deleteByPrimaryKey(studentLearningId);
    }

    @Override
    public List<StudentLearningDTO> getAllStudentInLearning(LoginUser loginUser, String learningContentId) {
        if (StringUtils.isBlank(learningContentId)) {
            throw new NullFiledException("学习内容ID为空");
        }
        if (learningContentMapper.countByPrimaryKey(learningContentId) == 0L) {
            throw new IdNotFoundException("学习内容不存在");
        }
        return studentLearningMapper.selectAllWithStudentName(learningContentId)
                .stream()
                .peek(studentLearningDTO -> studentLearningDTO.setStudentWork(studentWorkMapper.selectByPrimaryKey(studentLearningDTO.getId())))
                .collect(Collectors.toList());
    }
}
