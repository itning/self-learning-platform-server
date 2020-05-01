package com.project.selflearningplatformserver.service;

import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.entity.Subject;

import java.util.List;

/**
 * 科目分类
 *
 * @author itning
 * @date 2020/5/1 19:33
 */
public interface SubjectService {
    /**
     * 根据登录用户（教师）获取科目
     *
     * @param loginUser 登录用户
     * @return 科目集合
     */
    List<Subject> getAllSubject(LoginUser loginUser);

    /**
     * 删除科目
     *
     * @param loginUser 登录用户
     * @param subjectId 科目ID
     */
    void delSubject(LoginUser loginUser, String subjectId);

    /**
     * 新增科目
     *
     * @param loginUser 登录用户
     * @param name      科目名
     * @return 新增的科目
     */
    Subject newSubject(LoginUser loginUser, String name);

    /**
     * 更新科目
     *
     * @param loginUser 登录用户
     * @param subject   更新的科目
     * @return 更新的科目
     */
    Subject updateSubject(LoginUser loginUser, Subject subject);
}
