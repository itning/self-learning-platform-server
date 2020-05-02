package com.project.selflearningplatformserver.service;

import com.project.selflearningplatformserver.entity.Log;

import java.util.List;

/**
 * @author itning
 * @date 2020/5/2 17:03
 */
public interface LogService {
    /**
     * 获取系统日志
     *
     * @return 日志集合
     */
    List<Log> getSystemLog();
}
