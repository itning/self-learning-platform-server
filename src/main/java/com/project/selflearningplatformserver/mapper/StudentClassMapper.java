package com.project.selflearningplatformserver.mapper;

import com.project.selflearningplatformserver.dto.StudentClassDTO;
import com.project.selflearningplatformserver.dto.UserDTO;
import com.project.selflearningplatformserver.entity.StudentClass;

import java.util.List;

public interface StudentClassMapper {
    int deleteByPrimaryKey(String id);

    int insert(StudentClass record);

    int insertSelective(StudentClass record);

    StudentClass selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StudentClass record);

    int updateByPrimaryKey(StudentClass record);

    long countByPrimaryKey(String id);

    List<StudentClass> selectByUserId(String userId);

    List<StudentClassDTO> selectAllWithTeacherName();

    List<UserDTO> selectAllStudentInClass(String userId);
}