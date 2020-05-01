package com.project.selflearningplatformserver.mapper;

import com.project.selflearningplatformserver.entity.Attendance;

import java.util.List;

public interface AttendanceMapper {
    int deleteByPrimaryKey(String id);

    int insert(Attendance record);

    int insertSelective(Attendance record);

    Attendance selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Attendance record);

    int updateByPrimaryKey(Attendance record);

    List<Attendance> selectAll();

    List<Attendance> selectAllByUserRoleId(String roleId);

    long countByPrimaryKey(String id);
}