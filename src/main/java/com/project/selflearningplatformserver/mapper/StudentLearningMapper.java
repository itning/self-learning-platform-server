package com.project.selflearningplatformserver.mapper;

import com.project.selflearningplatformserver.entity.StudentLearning;

public interface StudentLearningMapper {
    int deleteByPrimaryKey(String id);

    int insert(StudentLearning record);

    int insertSelective(StudentLearning record);

    StudentLearning selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StudentLearning record);

    int updateByPrimaryKey(StudentLearning record);
}