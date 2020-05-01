package com.project.selflearningplatformserver.controller;

import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.dto.RestModel;
import com.project.selflearningplatformserver.entity.Examination;
import com.project.selflearningplatformserver.log.Log;
import com.project.selflearningplatformserver.security.MustTeacherLogin;
import com.project.selflearningplatformserver.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author itning
 * @date 2020/5/1 22:14
 */
@RestController
public class ExaminationController {
    private final ExaminationService examinationService;

    @Autowired
    public ExaminationController(ExaminationService examinationService) {
        this.examinationService = examinationService;
    }

    /**
     * 教师分页获取所有考试信息
     *
     * @param loginUser 登录用户
     * @param page      分页
     * @param size      每页数量
     * @return ResponseEntity
     */
    @GetMapping("/examinations")
    public ResponseEntity<?> allExaminationInfo(@MustTeacherLogin LoginUser loginUser,
                                                @RequestParam(defaultValue = "1") int page,
                                                @RequestParam(defaultValue = "20") int size) {
        return RestModel.ok(examinationService.getAllExamination(loginUser, page, size));
    }

    /**
     * 教师删除考试信息
     *
     * @param loginUser 登录用户
     * @param id        要删除的考试信息ID
     * @return ResponseEntity
     */
    @Log("删除考试信息")
    @DeleteMapping("/examination/{id}")
    public ResponseEntity<?> delExamination(@MustTeacherLogin LoginUser loginUser,
                                            @PathVariable String id) {
        examinationService.delExamination(loginUser, id);
        return RestModel.noContent();
    }

    /**
     * 教师新增考试信息
     *
     * @param loginUser 登录用户
     * @param name      考试信息名
     * @return ResponseEntity
     */
    @Log("新增考试信息")
    @PostMapping("/examination")
    public ResponseEntity<?> newExamination(@MustTeacherLogin LoginUser loginUser,
                                            @RequestParam String name) {
        return RestModel.created(examinationService.newExamination(loginUser, name));
    }

    /**
     * 教师修改考试信息
     *
     * @param loginUser   登录用户
     * @param examination 修改的考试信息
     * @return ResponseEntity
     */
    @Log("修改考试信息")
    @PatchMapping("/examination")
    public ResponseEntity<?> updateExamination(@MustTeacherLogin LoginUser loginUser,
                                               @RequestBody Examination examination) {
        examinationService.updateExamination(loginUser, examination);
        return RestModel.noContent();
    }
}
