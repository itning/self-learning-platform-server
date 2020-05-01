package com.project.selflearningplatformserver.service.impl;

import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.entity.StudentClass;
import com.project.selflearningplatformserver.exception.IdNotFoundException;
import com.project.selflearningplatformserver.exception.NullFiledException;
import com.project.selflearningplatformserver.mapper.StudentClassMapper;
import com.project.selflearningplatformserver.service.StudentClassServer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author itning
 * @date 2020/5/1 20:15
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class StudentClassServerImpl implements StudentClassServer {
    private final StudentClassMapper studentClassMapper;

    @Autowired
    public StudentClassServerImpl(StudentClassMapper studentClassMapper) {
        this.studentClassMapper = studentClassMapper;
    }

    @Override
    public List<StudentClass> getAll(LoginUser loginUser) {
        return studentClassMapper.selectByUserId(loginUser.getId());
    }

    @Override
    public void del(LoginUser loginUser, String id) {
        if (StringUtils.isBlank(id)) {
            throw new NullFiledException("班级ID为空");
        }
        if (studentClassMapper.countByPrimaryKey(id) == 0L) {
            throw new IdNotFoundException("班级ID不存在");
        }
        studentClassMapper.deleteByPrimaryKey(id);
    }

    @Override
    public StudentClass newStudentClass(LoginUser loginUser, String name) {
        if (StringUtils.isBlank(name)) {
            throw new NullFiledException("班级名为空");
        }
        Date date = new Date();
        StudentClass studentClass = new StudentClass();
        studentClass.setId(UUID.randomUUID().toString());
        studentClass.setName(name);
        studentClass.setUserId(loginUser.getId());
        studentClass.setGmtCreate(date);
        studentClass.setGmtModified(date);
        studentClassMapper.insert(studentClass);
        return studentClass;
    }

    @Override
    public StudentClass updateStudentClass(LoginUser loginUser, StudentClass studentClass) {
        if (StringUtils.isBlank(studentClass.getId())) {
            throw new NullFiledException("班级ID为空");
        }
        if (studentClassMapper.countByPrimaryKey(studentClass.getId()) == 0L) {
            throw new IdNotFoundException("班级ID不存在");
        }
        if (StringUtils.isBlank(studentClass.getName())) {
            throw new NullFiledException("班级名为空");
        }
        studentClass.setGmtCreate(null);
        studentClass.setGmtModified(null);
        studentClass.setUserId(null);
        studentClassMapper.updateByPrimaryKeySelective(studentClass);
        return studentClass;
    }
}
