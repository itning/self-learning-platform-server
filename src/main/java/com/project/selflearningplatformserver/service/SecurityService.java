package com.project.selflearningplatformserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.selflearningplatformserver.exception.SecurityServerException;

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
     * @throws SecurityServerException 登录失败抛出该异常
     * @throws JsonProcessingException 实体转成JSON失败抛出该异常
     * @see com.project.selflearningplatformserver.util.JwtUtils#buildJwt
     */
    String login(String username, String password) throws SecurityServerException, JsonProcessingException;
}
