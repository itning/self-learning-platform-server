package com.project.selflearningplatformserver.mapper;

import com.project.selflearningplatformserver.entity.StudentWork;
import org.apache.ibatis.annotations.Param;

public interface StudentWorkMapper {
    int deleteByPrimaryKey(String id);

    int insert(StudentWork record);

    int insertSelective(StudentWork record);

    StudentWork selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StudentWork record);

    int updateByPrimaryKey(StudentWork record);

    long countByPrimaryKey(String id);

    StudentWork selectByLearningContentIdAndStudentId(@Param("learningContentId") String learningContentId,
                                                      @Param("studentId") String studentId);
}