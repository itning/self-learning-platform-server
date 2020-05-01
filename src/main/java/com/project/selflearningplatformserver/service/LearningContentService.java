package com.project.selflearningplatformserver.service;

import com.github.pagehelper.PageInfo;
import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.entity.LearningContent;
import org.springframework.web.multipart.MultipartFile;

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
     * @param page      页码
     * @param size      每页数量
     * @return 分页学习内容集合
     */
    PageInfo<LearningContent> getAllBySubjectId(String subjectId, int page, int size);

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
     * @param subjectId 科目ID
     * @return 新增的学习内容
     */
    LearningContent newLearningContent(LoginUser loginUser, MultipartFile file, String subjectId);

    /**
     * 教师更新学习内容
     *
     * @param loginUser       登录用户
     * @param file            文件
     * @param learningContent 修改的学习内容
     * @return 修改的学习内容
     */
    LearningContent updateLearningContent(LoginUser loginUser, MultipartFile file, LearningContent learningContent);
}
