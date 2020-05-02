package com.project.selflearningplatformserver.controller;

import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.dto.RestModel;
import com.project.selflearningplatformserver.entity.ExaminationScore;
import com.project.selflearningplatformserver.log.Log;
import com.project.selflearningplatformserver.security.MustTeacherLogin;
import com.project.selflearningplatformserver.service.ExaminationScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author itning
 * @date 2020/5/1 22:24
 */
@RestController
public class ExaminationScoreController {
    private final ExaminationScoreService examinationScoreService;

    @Autowired
    public ExaminationScoreController(ExaminationScoreService examinationScoreService) {
        this.examinationScoreService = examinationScoreService;
    }


    /**
     * 教师分页获取所有考试分数
     *
     * @param loginUser     登录用户
     * @param examinationId 考试信息ID
     * @return ResponseEntity
     */
    @GetMapping("/examination_scores")
    public ResponseEntity<?> allExaminationScoreInfo(@MustTeacherLogin LoginUser loginUser,
                                                     @RequestParam String examinationId) {
        return RestModel.ok(examinationScoreService.getAllByExaminationId(loginUser, examinationId));
    }

    /**
     * 教师删除考试分数
     *
     * @param loginUser 登录用户
     * @param id        要删除的考试分数ID
     * @return ResponseEntity
     */
    @Log("删除考试分数")
    @DeleteMapping("/examination_score/{id}")
    public ResponseEntity<?> delExamination(@MustTeacherLogin LoginUser loginUser,
                                            @PathVariable String id) {
        examinationScoreService.del(loginUser, id);
        return RestModel.noContent();
    }

    /**
     * 教师新增考试分数
     *
     * @param loginUser        登录用户
     * @param examinationScore 考试分数
     * @return ResponseEntity
     */
    @Log("新增考试分数")
    @PostMapping("/examination_score")
    public ResponseEntity<?> newExaminationScore(@MustTeacherLogin LoginUser loginUser,
                                                 ExaminationScore examinationScore) {
        return RestModel.created(examinationScoreService.newExaminationScore(loginUser, examinationScore));
    }

    /**
     * 教师修改考试分数
     *
     * @param loginUser        登录用户
     * @param examinationScore 修改的考试分数
     * @return ResponseEntity
     */
    @Log("修改考试分数")
    @PatchMapping("/examination_score")
    public ResponseEntity<?> updateExaminationScore(@MustTeacherLogin LoginUser loginUser,
                                                    @RequestBody ExaminationScore examinationScore) {
        examinationScoreService.updateExaminationScore(loginUser, examinationScore);
        return RestModel.noContent();
    }
}
