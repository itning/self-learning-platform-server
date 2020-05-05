package com.project.selflearningplatformserver.service;

import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.entity.Teacher;

import java.util.List;

/**
 * @author itning
 * @date 2020/5/6 5:54
 */
public interface TeacherService {
    /**
     * 获取所有教师信息
     *
     * @param loginUser 登录信息
     * @return 教师信息集合
     */
    List<Teacher> getAllTeacherInfo(LoginUser loginUser);

    /**
     * 根据KEY删除教师信息
     *
     * @param loginUser    登录用户
     * @param id 教师信息ID
     */
    void delTeacherInfo(LoginUser loginUser, String id);

    /**
     * 新增教师用户信息
     *
     * @param loginUser      登录用户
     * @param attributeKey   属性KEY
     * @param attributeValue 属性VALUE
     * @return 新教师信息
     */
    Teacher newTeacherInfo(LoginUser loginUser, String attributeKey, String attributeValue);

    /**
     * 更新用户信息
     *
     * @param loginUser 登录用户
     * @param teacher   修改的教师信息
     * @return 教师信息
     */
    Teacher updateTeacherInfo(LoginUser loginUser, Teacher teacher);
}
