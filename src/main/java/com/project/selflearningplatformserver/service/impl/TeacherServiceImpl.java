package com.project.selflearningplatformserver.service.impl;

import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.entity.Teacher;
import com.project.selflearningplatformserver.exception.IdNotFoundException;
import com.project.selflearningplatformserver.exception.NullFiledException;
import com.project.selflearningplatformserver.exception.SecurityServerException;
import com.project.selflearningplatformserver.mapper.TeacherMapper;
import com.project.selflearningplatformserver.service.TeacherService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author itning
 * @date 2020/5/6 6:04
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherMapper teacherMapper;

    @Autowired
    public TeacherServiceImpl(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
    }

    @Override
    public List<Teacher> getAllTeacherInfo(LoginUser loginUser) {
        return teacherMapper.selectAllByUserId(loginUser.getId());
    }

    @Override
    public void delTeacherInfo(LoginUser loginUser, String id) {
        if (StringUtils.isBlank(id)) {
            throw new NullFiledException("教师信息ID为空");
        }
        if (teacherMapper.countByPrimaryKey(id) == 0L) {
            throw new IdNotFoundException("教师信息未找到");
        }
        teacherMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Teacher newTeacherInfo(LoginUser loginUser, String attributeKey, String attributeValue) {
        if (StringUtils.isAnyBlank(attributeKey, attributeValue)) {
            throw new NullFiledException("参数为空");
        }
        if (teacherMapper.countByAttributeKeyAndUserId(attributeKey, loginUser.getId()) > 0L) {
            throw new SecurityServerException("该属性已存在", HttpStatus.BAD_REQUEST);
        }
        Date date = new Date();
        Teacher teacher = new Teacher();
        teacher.setId(UUID.randomUUID().toString());
        teacher.setUserId(loginUser.getId());
        teacher.setAttributeKey(attributeKey);
        teacher.setAttributeValue(attributeValue);
        teacher.setGmtCreate(date);
        teacher.setGmtModified(date);
        teacherMapper.insert(teacher);
        return teacher;
    }

    @Override
    public Teacher updateTeacherInfo(LoginUser loginUser, Teacher teacher) {
        if (StringUtils.isAnyBlank(teacher.getId(), teacher.getAttributeKey(), teacher.getAttributeValue())) {
            throw new NullFiledException("参数为空");
        }
        if (teacherMapper.countByAttributeKeyAndUserId(teacher.getAttributeKey(), loginUser.getId()) == 0L) {
            throw new IdNotFoundException("该属性未找到");
        }
        if (teacherMapper.countByPrimaryKey(teacher.getId()) == 0L) {
            throw new IdNotFoundException("教师属性未找到");
        }
        teacher.setGmtCreate(null);
        teacher.setGmtModified(new Date());
        teacher.setUserId(null);
        teacherMapper.updateByPrimaryKeySelective(teacher);
        return teacher;
    }
}
