package com.project.selflearningplatformserver.service.impl;

import com.project.selflearningplatformserver.entity.Log;
import com.project.selflearningplatformserver.mapper.LogMapper;
import com.project.selflearningplatformserver.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

/**
 * @author itning
 * @date 2020/5/2 17:04
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class LogServiceImpl implements LogService {
    private final LogMapper logMapper;

    @Autowired
    public LogServiceImpl(LogMapper logMapper) {
        this.logMapper = logMapper;
    }

    @Override
    public List<Log> getSystemLog() {
        List<Log> logList = logMapper.selectAll();
        logList.sort(Comparator.comparing(Log::getGmtModified).reversed());
        return logList;
    }
}
