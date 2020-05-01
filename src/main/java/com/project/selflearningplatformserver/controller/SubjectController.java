package com.project.selflearningplatformserver.controller;

import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.dto.RestModel;
import com.project.selflearningplatformserver.entity.Subject;
import com.project.selflearningplatformserver.log.Log;
import com.project.selflearningplatformserver.security.MustTeacherLogin;
import com.project.selflearningplatformserver.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author itning
 * @date 2020/5/1 19:46
 */
@RestController
public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    /**
     * 根据教师信息获取科目集合
     *
     * @param loginUser 登录用户
     * @return ResponseEntity
     */
    @GetMapping("/subjects")
    public ResponseEntity<?> getAllSubjects(@MustTeacherLogin LoginUser loginUser) {
        return RestModel.ok(subjectService.getAllSubject(loginUser));
    }

    /**
     * 教师新增科目
     *
     * @param loginUser 登录用户
     * @param name      科目名
     * @return ResponseEntity
     */
    @Log("新增科目")
    @PostMapping("/subject")
    public ResponseEntity<?> newSubject(@MustTeacherLogin LoginUser loginUser,
                                        @RequestParam String name) {
        return RestModel.created(subjectService.newSubject(loginUser, name));
    }

    /**
     * 教师修改科目
     *
     * @param loginUser 登录用户
     * @param subject   科目信息
     * @return ResponseEntity
     */
    @Log("修改科目")
    @PatchMapping("/subject")
    public ResponseEntity<?> updateSubject(@MustTeacherLogin LoginUser loginUser,
                                           @RequestBody Subject subject) {
        subjectService.updateSubject(loginUser, subject);
        return RestModel.noContent();
    }

    /**
     * 教师删除科目
     *
     * @param loginUser 登录用户
     * @param subjectId 科目ID
     * @return ResponseEntity
     */
    @Log("删除科目")
    @DeleteMapping("/subject/{subjectId}")
    public ResponseEntity<?> delSubject(@MustTeacherLogin LoginUser loginUser,
                                        @PathVariable String subjectId) {
        subjectService.delSubject(loginUser, subjectId);
        return RestModel.noContent();
    }
}
