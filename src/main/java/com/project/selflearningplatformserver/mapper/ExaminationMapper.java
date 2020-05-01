package com.project.selflearningplatformserver.mapper;

import com.project.selflearningplatformserver.entity.Examination;

import java.util.List;

public interface ExaminationMapper {
    int deleteByPrimaryKey(String id);

    int insert(Examination record);

    int insertSelective(Examination record);

    Examination selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Examination record);

    int updateByPrimaryKey(Examination record);

    List<Examination> selectAllByUserId(String userId);
}