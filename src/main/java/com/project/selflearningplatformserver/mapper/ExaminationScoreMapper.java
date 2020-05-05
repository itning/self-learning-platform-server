package com.project.selflearningplatformserver.mapper;

import com.project.selflearningplatformserver.dto.ExaminationScoreWithExamName;
import com.project.selflearningplatformserver.entity.ExaminationScore;

import java.util.List;

public interface ExaminationScoreMapper {
    int deleteByPrimaryKey(String id);

    int insert(ExaminationScore record);

    int insertSelective(ExaminationScore record);

    ExaminationScore selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ExaminationScore record);

    int updateByPrimaryKey(ExaminationScore record);

    List<ExaminationScore> selectAllByExaminationId(String examinationId);

    List<ExaminationScoreWithExamName> selectStudentOwnExaminationScore(String studentId);
}