package com.project.selflearningplatformserver.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.dto.UserDTO;
import com.project.selflearningplatformserver.entity.Student;
import com.project.selflearningplatformserver.entity.User;
import com.project.selflearningplatformserver.exception.IdNotFoundException;
import com.project.selflearningplatformserver.exception.IllegalFiledException;
import com.project.selflearningplatformserver.exception.NullFiledException;
import com.project.selflearningplatformserver.exception.SecurityServerException;
import com.project.selflearningplatformserver.mapper.StudentClassMapper;
import com.project.selflearningplatformserver.mapper.StudentMapper;
import com.project.selflearningplatformserver.mapper.UserMapper;
import com.project.selflearningplatformserver.service.SecurityService;
import com.project.selflearningplatformserver.util.JwtUtils;
import com.project.selflearningplatformserver.util.Md5Utils;
import com.project.selflearningplatformserver.util.OrikaUtils;
import com.project.selflearningplatformserver.util.Tuple;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * @author itning
 * @date 2020/5/1 14:01
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SecurityServiceImpl implements SecurityService {
    private final UserMapper userMapper;
    private final StudentMapper studentMapper;
    private final StudentClassMapper studentClassMapper;

    @Autowired
    public SecurityServiceImpl(UserMapper userMapper, StudentMapper studentMapper, StudentClassMapper studentClassMapper) {
        this.userMapper = userMapper;
        this.studentMapper = studentMapper;
        this.studentClassMapper = studentClassMapper;
    }

    @Override
    public String login(String username, String password) throws JsonProcessingException {
        User user = userMapper.selectByUserName(username).orElseThrow(() -> new SecurityServerException("用户名不存在", HttpStatus.NOT_FOUND));
        if (!Md5Utils.checkEquals(password, user.getSalt(), user.getPassword())) {
            throw new SecurityServerException("密码错误", HttpStatus.BAD_REQUEST);
        }
        return JwtUtils.buildJwt(OrikaUtils.a2b(user, LoginUser.class));
    }

    @Override
    public UserDTO reg(String username, String password, String name, String studentClassId) {
        if (StringUtils.isNotBlank(studentClassId) && studentClassMapper.countByPrimaryKey(studentClassId) == 0L) {
            throw new IdNotFoundException("班级ID不存在");
        }
        if (StringUtils.isAnyBlank(username, password, name)) {
            throw new NullFiledException("传入参数为空");
        }
        if (userMapper.countByUserName(username) != 0L) {
            throw new IllegalFiledException("用户名已存在");
        }

        Tuple<String, String> md5 = Md5Utils.string2Md5(password);
        Date date = new Date();
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(name);
        user.setUsername(username);
        user.setPassword(md5.getT1());
        user.setFreeze(false);
        user.setSalt(md5.getT2());
        user.setRoleId(LoginUser.ROLE_STUDENT_ID);
        user.setGmtCreate(date);
        user.setGmtModified(date);
        userMapper.insert(user);

        if (StringUtils.isNotBlank(studentClassId)) {
            Student student = new Student();
            student.setUserId(user.getId());
            student.setStudentClassId(studentClassId);
            student.setGmtCreate(date);
            student.setGmtModified(date);
            studentMapper.insert(student);
        }
        return OrikaUtils.a2b(user, UserDTO.class);
    }
}
