package com.project.selflearningplatformserver.service;

import com.project.selflearningplatformserver.dto.LearningContentDTO;
import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.dto.StudentLearningDTO;
import com.project.selflearningplatformserver.entity.StudentLearning;

import java.util.List;

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
     * @return 分页的学习内容
     */
    List<LearningContentDTO> getMyLearning(LoginUser loginUser);

    /**
     * 学生取消学习该内容
     *
     * @param loginUser         登录用户
     * @param studentLearningId 学生学习ID
     */
    void delMyLearning(LoginUser loginUser, String studentLearningId);

    /**
     * 教师获取学生学习情况
     *
     * @param loginUser         登录用户
     * @param learningContentId 学习内容ID
     * @return 学生学习情况
     */
    List<StudentLearningDTO> getAllStudentInLearning(LoginUser loginUser, String learningContentId);
}
