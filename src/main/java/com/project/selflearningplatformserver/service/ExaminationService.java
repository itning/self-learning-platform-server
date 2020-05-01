package com.project.selflearningplatformserver.service;

import com.github.pagehelper.PageInfo;
import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.entity.Examination;

/**
 * 考试信息
 *
 * @author itning
 * @date 2020/5/1 21:03
 */
public interface ExaminationService {
    /**
     * 教师获取考试信息
     *
     * @param loginUser 登录用户
     * @param page      页码
     * @param size      每页数量
     * @return 分页的考试信息集合
     */
    PageInfo<Examination> getAllExamination(LoginUser loginUser, int page, int size);

    /**
     * 教师删除考试信息
     *
     * @param loginUser 登录用户
     * @param id        考试信息ID
     */
    void delExamination(LoginUser loginUser, String id);

    /**
     * 教师新增考试信息
     *
     * @param loginUser 登录用户
     * @param name      考试名
     * @return 新增的考试信息
     */
    Examination newExamination(LoginUser loginUser, String name);

    /**
     * 教师更新考试信息
     *
     * @param loginUser   登录用户
     * @param examination 更新的考试信息
     * @return 更新的考试信息
     */
    Examination updateExamination(LoginUser loginUser, Examination examination);
}
