package com.project.selflearningplatformserver.service;

import com.github.pagehelper.PageInfo;
import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.dto.UserDTO;
import com.project.selflearningplatformserver.entity.User;

import java.util.List;

/**
 * @author itning
 * @date 2020/5/1 14:27
 */
public interface UserService {
    /**
     * 获取教师信息
     *
     * @return 分页后的教师信息
     */
    List<UserDTO> getAllTeacherUser();

    /**
     * 分页获取学生信息
     *
     * @return 分页后的学生信息
     */
    List<UserDTO> getAllStudentUser();

    /**
     * 新增教师
     *
     * @param name     教师名
     * @param username 教师用户名
     * @return 新增的教师信息
     */
    UserDTO newTeacher(String name, String username);

    /**
     * <p>修改用户
     *
     * @param loginUser 登录用户
     * @param user      修改的信息
     */
    void updateUserInfo(LoginUser loginUser, User user);

    /**
     * 删除用户信息
     *
     * @param userId 用户ID
     */
    void delUserInfo(String userId);
}
