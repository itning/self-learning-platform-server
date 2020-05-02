package com.project.selflearningplatformserver.service;

import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.dto.StudentClassDTO;
import com.project.selflearningplatformserver.dto.UserDTO;
import com.project.selflearningplatformserver.entity.StudentClass;

import java.util.List;

/**
 * 教师创建的班级
 *
 * @author itning
 * @date 2020/5/1 20:02
 */
public interface StudentClassServer {
    /**
     * 获取教师所有班级
     *
     * @param loginUser 登录用户
     * @return 班级集合
     */
    List<StudentClass> getAll(LoginUser loginUser);

    /**
     * 删除班级
     *
     * @param loginUser 登录用户
     * @param id        班级ID
     */
    void del(LoginUser loginUser, String id);

    /**
     * 教师新增班级
     *
     * @param loginUser 登录用户
     * @param name      班级名
     * @return 新增的班级
     */
    StudentClass newStudentClass(LoginUser loginUser, String name);

    /**
     * 教师修改班级
     *
     * @param loginUser    登录用户
     * @param studentClass 修改的班级信息
     * @return 修改的班级
     */
    StudentClass updateStudentClass(LoginUser loginUser, StudentClass studentClass);

    /**
     * 获取所有班级信息
     *
     * @return 班级集合
     */
    List<StudentClassDTO> getAll();

    /**
     * 获取某教师的所有班级学生
     *
     * @param loginUser 登录用户
     * @return 所有班级学生
     */
    List<UserDTO> getAllStudentInClass(LoginUser loginUser);
}
