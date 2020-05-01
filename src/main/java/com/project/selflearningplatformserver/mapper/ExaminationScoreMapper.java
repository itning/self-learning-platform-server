package com.project.selflearningplatformserver.mapper;

import com.project.selflearningplatformserver.entity.ExaminationScore;

public interface ExaminationScoreMapper {
    int deleteByPrimaryKey(String id);

    int insert(ExaminationScore record);

    int insertSelective(ExaminationScore record);

    ExaminationScore selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ExaminationScore record);

    int updateByPrimaryKey(ExaminationScore record);
}