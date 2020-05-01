package com.project.selflearningplatformserver.mapper;

import com.project.selflearningplatformserver.entity.LearningContent;

public interface LearningContentMapper {
    int deleteByPrimaryKey(String id);

    int insert(LearningContent record);

    int insertSelective(LearningContent record);

    LearningContent selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LearningContent record);

    int updateByPrimaryKey(LearningContent record);
}