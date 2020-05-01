package com.project.selflearningplatformserver.mapper;

import com.project.selflearningplatformserver.entity.Subject;

import java.util.List;

public interface SubjectMapper {
    int deleteByPrimaryKey(String id);

    int insert(Subject record);

    int insertSelective(Subject record);

    Subject selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Subject record);

    int updateByPrimaryKey(Subject record);

    List<Subject> selectByUserId(String userId);

    long countByPrimaryKey(String id);
}