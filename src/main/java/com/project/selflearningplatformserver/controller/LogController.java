package com.project.selflearningplatformserver.controller;

import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.dto.RestModel;
import com.project.selflearningplatformserver.security.MustAdminLogin;
import com.project.selflearningplatformserver.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author itning
 * @date 2020/5/2 17:03
 */
@RestController
public class LogController {
    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    /**
     * 管理员获取系统日志
     *
     * @param loginUser 登陆用户
     * @return ResponseEntity
     */
    @GetMapping("/system_log")
    public ResponseEntity<?> getLogs(@MustAdminLogin LoginUser loginUser) {
        return RestModel.ok(logService.getSystemLog());
    }
}
