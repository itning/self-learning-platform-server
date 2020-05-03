package com.project.selflearningplatformserver.service;

import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.entity.StudentWork;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletResponse;

/**
 * @author itning
 * @date 2020/5/2 11:45
 */
public interface StudentWorkService {
    /**
     * 教师批改作业
     *
     * @param loginUser     登录用户
     * @param studentWorkId 学生作业ID
     * @param suggest       建议
     * @param score         分数
     * @return 批改完的学生作业
     */
    StudentWork teacherReview(LoginUser loginUser, String studentWorkId, String suggest, int score);

    /**
     * 学生删除作业
     *
     * @param loginUser     登录用户
     * @param studentWorkId 学生作业ID
     */
    void delWork(LoginUser loginUser, String studentWorkId);

    /**
     * 下载作业
     *
     * @param loginUser 登录用户
     * @param id        作业ID
     * @param response  {@link HttpServletResponse}
     * @param range     {@link HttpHeaders#RANGE}
     */
    void downloadWorkFile(LoginUser loginUser, String id, HttpServletResponse response, String range);
}
