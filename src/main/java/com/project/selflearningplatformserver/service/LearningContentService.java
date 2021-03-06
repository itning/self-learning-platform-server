package com.project.selflearningplatformserver.service;

import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.entity.LearningContent;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 学习内容
 *
 * @author itning
 * @date 2020/5/1 20:32
 */
public interface LearningContentService {
    /**
     * 分页获取学习内容
     *
     * @param subjectId 科目ID
     * @return 分页学习内容集合
     */
    List<LearningContent> getAllBySubjectId(String subjectId);

    /**
     * 教师删除学习内容
     *
     * @param loginUser 登录用户
     * @param id        学习内容ID
     */
    void delLearningContent(LoginUser loginUser, String id);

    /**
     * 教师新增学习内容
     *
     * @param loginUser 登录用户
     * @param file      文件
     * @param aidFile   其他文件
     * @param subjectId 科目ID
     * @param name      名称
     * @return 新增的学习内容
     */
    LearningContent newLearningContent(LoginUser loginUser, MultipartFile file, MultipartFile aidFile, String subjectId, String name);

    /**
     * 教师更新学习内容
     *
     * @param loginUser 登录用户
     * @return 修改的学习内容
     */
    LearningContent updateLearningContent(LoginUser loginUser, String id, String name);

    /**
     * 学生获取所有可以学习的内容（排除已经选择的）
     *
     * @param loginUser 登录用户
     * @return 学习内容集合
     */
    List<LearningContent> getAllCanLearningContent(LoginUser loginUser);

    /**
     * 下载学习内容文件
     *
     * @param loginUser         登录用户
     * @param learningContentId 学习内容ID
     * @param range             {@link org.springframework.http.HttpHeaders#RANGE}
     * @param response          {@link HttpServletResponse}
     * @param type              aid or video
     */
    void downloadLearningContent(LoginUser loginUser, String learningContentId, String range, HttpServletResponse response, String type);
}
