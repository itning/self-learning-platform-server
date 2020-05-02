package com.project.selflearningplatformserver.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.entity.StudentLearning;
import com.project.selflearningplatformserver.exception.IdNotFoundException;
import com.project.selflearningplatformserver.exception.NullFiledException;
import com.project.selflearningplatformserver.mapper.LearningContentMapper;
import com.project.selflearningplatformserver.mapper.StudentLearningMapper;
import com.project.selflearningplatformserver.service.StudentLearningService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * @author itning
 * @date 2020/5/2 11:29
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class StudentLearningServiceImpl implements StudentLearningService {
    private final StudentLearningMapper studentLearningMapper;
    private final LearningContentMapper learningContentMapper;

    @Autowired
    public StudentLearningServiceImpl(StudentLearningMapper studentLearningMapper, LearningContentMapper learningContentMapper) {
        this.studentLearningMapper = studentLearningMapper;
        this.learningContentMapper = learningContentMapper;
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
    public PageInfo<StudentLearning> getMyLearning(LoginUser loginUser, int page, int size) {
        PageHelper.startPage(page, size);
        return new PageInfo<>(studentLearningMapper.selectAllByStudentId(loginUser.getId()));
    }

    @Override
    public void delMyLearning(LoginUser loginUser, String studentLearningId) {
        if (StringUtils.isBlank(studentLearningId)) {
            throw new NullFiledException("学生学习ID为空");
        }
        if (studentLearningMapper.countByPrimaryKey(studentLearningId) == 0L) {
            throw new IdNotFoundException("学生学习ID不存在");
        }
        studentLearningMapper.deleteByPrimaryKey(studentLearningId);
    }
}
