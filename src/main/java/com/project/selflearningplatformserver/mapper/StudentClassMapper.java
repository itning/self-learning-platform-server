package com.project.selflearningplatformserver.mapper;

import com.project.selflearningplatformserver.entity.StudentClass;

public interface StudentClassMapper {
    int deleteByPrimaryKey(String id);

    int insert(StudentClass record);

    int insertSelective(StudentClass record);

    StudentClass selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StudentClass record);

    int updateByPrimaryKey(StudentClass record);

    long countByPrimaryKey(String id);
}