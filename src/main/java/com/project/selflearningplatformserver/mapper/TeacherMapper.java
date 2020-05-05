package com.project.selflearningplatformserver.mapper;

import com.project.selflearningplatformserver.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper {
    int deleteByPrimaryKey(String id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    List<Teacher> selectAllByUserId(String userId);

    long countByPrimaryKey(String id);

    long countByAttributeKeyAndUserId(@Param("attributeKey") String attributeKey, @Param("userId") String userId);
}