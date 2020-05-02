package com.project.selflearningplatformserver.mapper;

import com.project.selflearningplatformserver.entity.Log;

import java.util.List;

public interface LogMapper {
    int deleteByPrimaryKey(String id);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);

    List<Log> selectAll();
}