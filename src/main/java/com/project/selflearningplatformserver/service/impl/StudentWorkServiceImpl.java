package com.project.selflearningplatformserver.service.impl;

import com.project.selflearningplatformserver.config.AppProperties;
import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.entity.StudentLearning;
import com.project.selflearningplatformserver.entity.StudentWork;
import com.project.selflearningplatformserver.exception.IdNotFoundException;
import com.project.selflearningplatformserver.exception.NullFiledException;
import com.project.selflearningplatformserver.exception.SecurityServerException;
import com.project.selflearningplatformserver.mapper.LearningContentMapper;
import com.project.selflearningplatformserver.mapper.StudentLearningMapper;
import com.project.selflearningplatformserver.mapper.StudentWorkMapper;
import com.project.selflearningplatformserver.service.StudentWorkService;
import com.project.selflearningplatformserver.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

/**
 * @author itning
 * @date 2020/5/2 11:50
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service
public class StudentWorkServiceImpl implements StudentWorkService {
    private final StudentWorkMapper studentWorkMapper;
    private final StudentLearningMapper studentLearningMapper;
    private final LearningContentMapper learningContentMapper;
    private final AppProperties appProperties;

    @Autowired
    public StudentWorkServiceImpl(StudentWorkMapper studentWorkMapper, StudentLearningMapper studentLearningMapper, LearningContentMapper learningContentMapper, AppProperties appProperties) {
        this.studentWorkMapper = studentWorkMapper;
        this.studentLearningMapper = studentLearningMapper;
        this.learningContentMapper = learningContentMapper;
        this.appProperties = appProperties;
    }

    @Override
    public StudentWork teacherReview(LoginUser loginUser, String studentWorkId, String suggest, int score) {
        if (StringUtils.isAnyBlank(studentWorkId, suggest)) {
            throw new NullFiledException("参数为空");
        }
        if (score < 0 || score > 100) {
            throw new SecurityServerException("分数请大于0小于100", HttpStatus.BAD_REQUEST);
        }
        StudentWork studentWork = studentWorkMapper.selectByPrimaryKey(studentWorkId);
        if (Objects.isNull(studentWork)) {
            throw new IdNotFoundException("学生作业不存在");
        }
        studentWork.setFileUri(null);
        studentWork.setGmtCreate(null);
        studentWork.setGmtModified(new Date());
        studentWork.setScore(score);
        studentWork.setSuggest(suggest);
        studentWorkMapper.updateByPrimaryKeySelective(studentWork);
        return studentWork;
    }

    @Override
    public void delWork(LoginUser loginUser, String studentWorkId) {
        if (StringUtils.isNotBlank(studentWorkId)) {
            throw new NullFiledException("作业ID为空");
        }
        StudentLearning studentLearning = studentLearningMapper.selectByPrimaryKey(studentWorkId);
        if (Objects.isNull(studentLearning)) {
            throw new IdNotFoundException("学生学习ID不存在");
        }
        if (!studentLearning.getStudentId().equals(loginUser.getId())) {
            throw new SecurityServerException("删除失败", HttpStatus.FORBIDDEN);
        }
        StudentWork studentWork = studentWorkMapper.selectByPrimaryKey(studentWorkId);
        if (Objects.isNull(studentWork)) {
            throw new IdNotFoundException("学生作业不存在");
        }
        File file = new File(appProperties.getStudentWorkDir() + studentWork.getFileUri());
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
        studentWorkMapper.deleteByPrimaryKey(studentWorkId);
    }

    @Override
    public void downloadWorkFile(LoginUser loginUser, String id, HttpServletResponse response, String range) {
        if (StringUtils.isBlank(id)) {
            throw new NullFiledException("作业ID为空");
        }
        StudentWork studentWork = studentWorkMapper.selectByPrimaryKey(id);
        if (Objects.isNull(studentWork)) {
            throw new IdNotFoundException("作业未找到");
        }
        File file = new File(appProperties.getStudentWorkDir() + studentWork.getFileUri());
        if (!file.exists()) {
            throw new SecurityServerException("文件丢失", HttpStatus.NOT_FOUND);
        }
        FileUtils.breakpointResume(file, studentWork.getMime(), range, response);
    }

    @Override
    public StudentWork upload(LoginUser loginUser, String studentLearningId, MultipartFile file) {
        if (StringUtils.isBlank(studentLearningId)) {
            throw new NullFiledException("学习内容ID为空");
        }
        if (studentLearningMapper.countByPrimaryKey(studentLearningId) == 0L) {
            throw new IdNotFoundException("学习内容不存在");
        }

        String filenameExtension = org.springframework.util.StringUtils.getFilenameExtension(file.getOriginalFilename());
        String newFileName = studentLearningId + "." + filenameExtension;
        try {
            file.transferTo(new File(appProperties.getStudentWorkDir() + newFileName));
            Date date = new Date();
            StudentWork studentWork = new StudentWork();
            studentWork.setId(studentLearningId);
            studentWork.setFileUri(newFileName);
            studentWork.setMime(file.getContentType());
            studentWork.setExtensionName(filenameExtension);
            studentWork.setSize(file.getSize());
            studentWork.setGmtModified(date);
            if (studentWorkMapper.countByPrimaryKey(studentLearningId) != 0L) {
                // 覆盖上传
                studentWorkMapper.updateByPrimaryKeySelective(studentWork);
                return studentWork;
            }
            studentWork.setGmtCreate(date);
            studentWorkMapper.insertSelective(studentWork);
            return studentWork;
        } catch (IOException e) {
            if (log.isDebugEnabled()) {
                log.debug("Upload Exception {}", e.getMessage());
            }
            throw new SecurityServerException("上传错误", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public StudentWork getOwnWork(LoginUser loginUser, String learningContentId) {
        if (StringUtils.isBlank(learningContentId)) {
            throw new NullFiledException("学习内容ID为空");
        }
        if (learningContentMapper.countByPrimaryKey(learningContentId) == 0L) {
            throw new IdNotFoundException("学习内容ID不存在");
        }
        return studentWorkMapper.selectByLearningContentIdAndStudentId(learningContentId, loginUser.getId());
    }
}
