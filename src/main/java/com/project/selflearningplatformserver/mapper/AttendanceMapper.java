package com.project.selflearningplatformserver.mapper;

import com.project.selflearningplatformserver.entity.Attendance;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
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

    long countUserAttendanceInDate(@Param("userId") String userId, @Param("date") LocalDate date);
}