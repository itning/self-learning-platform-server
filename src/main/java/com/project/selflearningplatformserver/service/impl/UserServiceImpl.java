package com.project.selflearningplatformserver.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.dto.UserDTO;
import com.project.selflearningplatformserver.entity.User;
import com.project.selflearningplatformserver.exception.IdNotFoundException;
import com.project.selflearningplatformserver.exception.IllegalFiledException;
import com.project.selflearningplatformserver.exception.NullFiledException;
import com.project.selflearningplatformserver.exception.SecurityServerException;
import com.project.selflearningplatformserver.mapper.UserMapper;
import com.project.selflearningplatformserver.service.UserService;
import com.project.selflearningplatformserver.util.Md5Utils;
import com.project.selflearningplatformserver.util.OrikaUtils;
import com.project.selflearningplatformserver.util.Tuple;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author itning
 * @date 2020/5/1 14:38
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public PageInfo<UserDTO> getAllTeacherUser(int page, int size) {
        PageHelper.startPage(page, size);
        return new PageInfo<>(userMapper.selectAllByRoleId(LoginUser.ROLE_TEACHER_ID).stream().map(user -> OrikaUtils.a2b(user, UserDTO.class)).collect(Collectors.toList()));
    }

    @Override
    public PageInfo<UserDTO> getAllStudentUser(int page, int size) {
        PageHelper.startPage(page, size);
        return new PageInfo<>(userMapper.selectAllByRoleId(LoginUser.ROLE_STUDENT_ID).stream().map(user -> OrikaUtils.a2b(user, UserDTO.class)).collect(Collectors.toList()));
    }

    @Override
    public UserDTO newTeacher(String name, String username) {
        if (userMapper.countByUserName(username) != 0L) {
            throw new IllegalFiledException("用户名已存在");
        }
        Tuple<String, String> md5 = Md5Utils.string2Md5(username);
        Date date = new Date();
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(name);
        user.setUsername(username);
        user.setPassword(md5.getT1());
        user.setFreeze(false);
        user.setSalt(md5.getT2());
        user.setRoleId(LoginUser.ROLE_TEACHER_ID);
        user.setGmtCreate(date);
        user.setGmtModified(date);
        userMapper.insert(user);
        return OrikaUtils.a2b(user, UserDTO.class);
    }

    @Override
    public void updateUserInfo(LoginUser loginUser, User user) {
        user.setGmtCreate(null);
        user.setGmtModified(null);
        user.setUsername(null);
        // 管理员修改用户信息
        if (LoginUser.ROLE_ADMIN_ID.equals(loginUser.getRoleId())) {
            // 未传ID则修改管理员自己的信息
            if (StringUtils.isBlank(user.getId())) {
                user.setId(loginUser.getId());
            }
        } else {
            // 非管理员保证修改自己的信息
            user.setId(loginUser.getId());
            // 某些字段普通用户无法修改
            user.setFreeze(null);
            user.setRoleId(null);
        }
        // 用户更改密码
        if (StringUtils.isNotBlank(user.getPassword())) {
            Tuple<String, String> md5 = Md5Utils.string2Md5(user.getPassword());
            user.setPassword(md5.getT1());
            user.setSalt(md5.getT2());
        }
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void delUserInfo(String userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (Objects.isNull(user)) {
            throw new IdNotFoundException("用户ID不存在");
        }
        if (LoginUser.ROLE_ADMIN_ID.equals(user.getRoleId())) {
            throw new SecurityServerException("无法删除管理员账户", HttpStatus.BAD_REQUEST);
        }
        userMapper.deleteByPrimaryKey(userId);
    }
}
