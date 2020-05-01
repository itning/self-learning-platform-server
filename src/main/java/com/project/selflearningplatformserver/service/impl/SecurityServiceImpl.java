package com.project.selflearningplatformserver.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.entity.User;
import com.project.selflearningplatformserver.exception.SecurityServerException;
import com.project.selflearningplatformserver.mapper.UserMapper;
import com.project.selflearningplatformserver.service.SecurityService;
import com.project.selflearningplatformserver.util.JwtUtils;
import com.project.selflearningplatformserver.util.Md5Utils;
import com.project.selflearningplatformserver.util.OrikaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author itning
 * @date 2020/5/1 14:01
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SecurityServiceImpl implements SecurityService {
    private final UserMapper userMapper;

    @Autowired
    public SecurityServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public String login(String username, String password) throws SecurityServerException, JsonProcessingException {
        User user = userMapper.selectByUserName(username).orElseThrow(() -> new SecurityServerException("用户名不存在", HttpStatus.NOT_FOUND));
        if (!Md5Utils.checkEquals(password, user.getSalt(), user.getPassword())) {
            throw new SecurityServerException("密码错误", HttpStatus.BAD_REQUEST);
        }
        return JwtUtils.buildJwt(OrikaUtils.a2b(user, LoginUser.class));
    }
}
