package com.project.selflearningplatformserver.mapper;

import com.project.selflearningplatformserver.dto.LearningContentDTO;
import com.project.selflearningplatformserver.dto.StudentLearningDTO;
import com.project.selflearningplatformserver.entity.StudentLearning;

import java.util.List;

public interface StudentLearningMapper {
    int deleteByPrimaryKey(String id);

    int insert(StudentLearning record);

    int insertSelective(StudentLearning record);

    StudentLearning selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StudentLearning record);

    int updateByPrimaryKey(StudentLearning record);

    List<LearningContentDTO> selectAllByStudentId(String studentId);

    long countByPrimaryKey(String id);

    List<StudentLearningDTO> selectAllWithStudentName(String learningContentId);
}