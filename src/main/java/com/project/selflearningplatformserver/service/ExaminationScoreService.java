package com.project.selflearningplatformserver.service;

import com.github.pagehelper.PageInfo;
import com.project.selflearningplatformserver.dto.ExaminationScoreDTO;
import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.entity.ExaminationScore;

/**
 * 某考试信息所有分数
 *
 * @author itning
 * @date 2020/5/1 21:29
 */
public interface ExaminationScoreService {
    /**
     * 教师分页考取考试分数信息
     *
     * @param loginUser     登录用户
     * @param examinationId 考试信息ID
     * @param page          分页
     * @param size          每页数量
     * @return 分页的考试分数信息
     */
    PageInfo<ExaminationScoreDTO> getAllByExaminationId(LoginUser loginUser, String examinationId, int page, int size);

    /**
     * 教师删除考试分数信息
     *
     * @param loginUser 登录用户
     * @param id        考试分数信息ID
     */
    void del(LoginUser loginUser, String id);

    /**
     * 教师新增考试信息
     *
     * @param loginUser        登录用户
     * @param examinationScore 考试信息
     * @return 新增的考试信息
     */
    ExaminationScoreDTO newExaminationScore(LoginUser loginUser, ExaminationScore examinationScore);

    /**
     * 教师更新考试信息
     *
     * @param loginUser        登录用户
     * @param examinationScore 考试信息
     * @return 更新的考试信息
     */
    ExaminationScoreDTO updateExaminationScore(LoginUser loginUser, ExaminationScore examinationScore);
}