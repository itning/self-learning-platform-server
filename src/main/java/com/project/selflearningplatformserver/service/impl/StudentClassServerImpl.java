package com.project.selflearningplatformserver.service.impl;

import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.dto.StudentClassDTO;
import com.project.selflearningplatformserver.dto.UserDTO;
import com.project.selflearningplatformserver.entity.Student;
import com.project.selflearningplatformserver.entity.StudentClass;
import com.project.selflearningplatformserver.exception.IdNotFoundException;
import com.project.selflearningplatformserver.exception.NullFiledException;
import com.project.selflearningplatformserver.mapper.StudentClassMapper;
import com.project.selflearningplatformserver.mapper.StudentMapper;
import com.project.selflearningplatformserver.service.StudentClassServer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author itning
 * @date 2020/5/1 20:15
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class StudentClassServerImpl implements StudentClassServer {
    private final StudentClassMapper studentClassMapper;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentClassServerImpl(StudentClassMapper studentClassMapper, StudentMapper studentMapper) {
        this.studentClassMapper = studentClassMapper;
        this.studentMapper = studentMapper;
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
        studentClass.setGmtModified(new Date());
        studentClass.setUserId(null);
        studentClassMapper.updateByPrimaryKeySelective(studentClass);
        return studentClass;
    }

    @Override
    public List<StudentClassDTO> getAll() {
        return studentClassMapper.selectAllWithTeacherName();
    }

    @Override
    public List<UserDTO> getAllStudentInClass(LoginUser loginUser) {
        return studentClassMapper.selectAllStudentInClass(loginUser.getId());
    }

    @Override
    public StudentClassDTO getStudentOwnClass(LoginUser loginUser) {
        return studentClassMapper.selectOwnClass(loginUser.getId());
    }

    @Override
    public Student joinClass(LoginUser loginUser, String classId) {
        if (StringUtils.isBlank(classId)) {
            throw new NullFiledException("班级ID为空");
        }
        if (studentClassMapper.countByPrimaryKey(classId) == 0L) {
            throw new IdNotFoundException("班级ID不存在");
        }
        Student student = studentMapper.selectByPrimaryKey(loginUser.getId());
        if (Objects.isNull(student)) {
            // 新增
            Date date = new Date();
            Student s = new Student();
            s.setUserId(loginUser.getId());
            s.setStudentClassId(classId);
            s.setGmtCreate(date);
            s.setGmtModified(date);
            studentMapper.insert(s);
            return s;
        } else {
            // 覆盖
            student.setGmtCreate(null);
            student.setStudentClassId(classId);
            student.setGmtModified(new Date());
            studentMapper.updateByPrimaryKeySelective(student);
            return student;
        }
    }
}
