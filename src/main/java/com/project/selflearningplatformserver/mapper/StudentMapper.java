package com.project.selflearningplatformserver.mapper;

import com.project.selflearningplatformserver.entity.Student;

public interface StudentMapper {
    int deleteByPrimaryKey(String userId);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}