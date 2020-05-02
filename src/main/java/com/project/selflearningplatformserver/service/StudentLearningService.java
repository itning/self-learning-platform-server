package com.project.selflearningplatformserver.service;

import com.github.pagehelper.PageInfo;
import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.entity.StudentLearning;

/**
 * @author itning
 * @date 2020/5/2 11:24
 */
public interface StudentLearningService {
    /**
     * 学生选择学习内容进行学习
     *
     * @param loginUser         登录用户
     * @param learningContentId 学习内容ID
     * @return 选择
     */
    StudentLearning switchLearningContent(LoginUser loginUser, String learningContentId);

    /**
     * 学生获取自己的学习内容
     *
     * @param loginUser 登录用户
     * @param page      页码
     * @param size      每页数量
     * @return 分页的学习内容
     */
    PageInfo<StudentLearning> getMyLearning(LoginUser loginUser, int page, int size);

    /**
     * 学生取消学习该内容
     *
     * @param loginUser         登录用户
     * @param studentLearningId 学生学习ID
     */
    void delMyLearning(LoginUser loginUser, String studentLearningId);
}
