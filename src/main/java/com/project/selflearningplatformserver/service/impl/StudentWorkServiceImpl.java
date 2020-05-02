package com.project.selflearningplatformserver.service.impl;

import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.entity.StudentLearning;
import com.project.selflearningplatformserver.entity.StudentWork;
import com.project.selflearningplatformserver.exception.IdNotFoundException;
import com.project.selflearningplatformserver.exception.NullFiledException;
import com.project.selflearningplatformserver.exception.SecurityServerException;
import com.project.selflearningplatformserver.mapper.StudentLearningMapper;
import com.project.selflearningplatformserver.mapper.StudentWorkMapper;
import com.project.selflearningplatformserver.service.StudentWorkService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

/**
 * @author itning
 * @date 2020/5/2 11:50
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class StudentWorkServiceImpl implements StudentWorkService {
    private final StudentWorkMapper studentWorkMapper;
    private final StudentLearningMapper studentLearningMapper;

    @Autowired
    public StudentWorkServiceImpl(StudentWorkMapper studentWorkMapper, StudentLearningMapper studentLearningMapper) {
        this.studentWorkMapper = studentWorkMapper;
        this.studentLearningMapper = studentLearningMapper;
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
        StudentLearning studentLearning = studentLearningMapper.selectByPrimaryKey(studentWorkId);
        if (Objects.isNull(studentLearning)) {
            throw new IdNotFoundException("学生学习ID不存在");
        }
        if (!studentLearning.getStudentId().equals(loginUser.getId())) {
            throw new SecurityServerException("删除失败", HttpStatus.FORBIDDEN);
        }
        studentWorkMapper.deleteByPrimaryKey(studentWorkId);
    }
}
