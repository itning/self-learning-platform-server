package com.project.selflearningplatformserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.selflearningplatformserver.dto.UserDTO;

/**
 * @author itning
 * @date 2020/5/1 13:58
 */
public interface SecurityService {
    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录成功后会返回TOKEN
     * @throws JsonProcessingException 实体转成JSON失败抛出该异常
     * @see com.project.selflearningplatformserver.util.JwtUtils#buildJwt
     */
    String login(String username, String password) throws JsonProcessingException;

    /**
     * 学生注册
     *
     * @param username       用户名
     * @param password       密码
     * @param name           姓名
     * @param studentClassId 所属班级
     * @return 注册成功的学生信息
     */
    UserDTO reg(String username, String password, String name, String studentClassId);
}
