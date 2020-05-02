package com.project.selflearningplatformserver.mapper;

import com.project.selflearningplatformserver.entity.LearningContent;

import java.util.List;

public interface LearningContentMapper {
    int deleteByPrimaryKey(String id);

    int insert(LearningContent record);

    int insertSelective(LearningContent record);

    LearningContent selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LearningContent record);

    int updateByPrimaryKey(LearningContent record);

    List<LearningContent> selectAllBySubjectId(String subjectId);

    long countByPrimaryKey(String id);
}