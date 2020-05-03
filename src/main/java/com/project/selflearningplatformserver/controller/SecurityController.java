package com.project.selflearningplatformserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.selflearningplatformserver.dto.RestModel;
import com.project.selflearningplatformserver.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 安全相关，登录注册等功能
 *
 * @author itning
 * @date 2020/5/1 13:58
 */
@RestController
@RequestMapping("/security")
public class SecurityController {
    private final SecurityService securityService;

    @Autowired
    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return ResponseEntity
     * @throws JsonProcessingException see {@link SecurityService#login}
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username,
                                   @RequestParam String password) throws JsonProcessingException {
        return RestModel.ok(securityService.login(username, password));
    }

    /**
     * 学生注册
     *
     * @param username       用户名
     * @param password       密码
     * @param name           姓名
     * @param studentClassId 所属班级ID
     * @return ResponseEntity
     */
    @PostMapping("/reg")
    public ResponseEntity<?> registration(@RequestParam String username,
                                          @RequestParam String password,
                                          @RequestParam String name,
                                          @RequestParam String studentClassId) {
        return RestModel.created(securityService.reg(username, password, name, studentClassId));
    }
}
