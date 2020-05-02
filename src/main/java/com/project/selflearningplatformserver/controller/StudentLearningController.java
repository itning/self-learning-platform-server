package com.project.selflearningplatformserver.controller;

import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.dto.RestModel;
import com.project.selflearningplatformserver.log.Log;
import com.project.selflearningplatformserver.security.MustStudentLogin;
import com.project.selflearningplatformserver.service.StudentLearningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 学生学习
 *
 * @author itning
 * @date 2020/5/2 11:22
 */
@RestController
public class StudentLearningController {
    private final StudentLearningService studentLearningService;

    @Autowired
    public StudentLearningController(StudentLearningService studentLearningService) {
        this.studentLearningService = studentLearningService;
    }

    /**
     * 学生选择学习内容
     *
     * @param loginUser         登录用户
     * @param learningContentId 学习内容ID
     * @return ResponseEntity
     */
    @Log("学生选择学习内容")
    @PostMapping("/student_learning")
    public ResponseEntity<?> switchLearningContent(@MustStudentLogin LoginUser loginUser,
                                                   @RequestParam String learningContentId) {
        return RestModel.created(studentLearningService.switchLearningContent(loginUser, learningContentId));
    }

    /**
     * 获取我的学习
     *
     * @param loginUser 登录用户
     * @param page      分页
     * @param size      每页数量
     * @return ResponseEntity
     */
    @GetMapping("/student_learning")
    public ResponseEntity<?> getMyStudy(@MustStudentLogin LoginUser loginUser,
                                        @RequestParam(defaultValue = "1") int page,
                                        @RequestParam(defaultValue = "20") int size) {
        return RestModel.ok(studentLearningService.getMyLearning(loginUser, page, size));
    }

    /**
     * 学生取消学习
     *
     * @param loginUser 登录用户
     * @param id        学生学习ID
     * @return ResponseEntity
     */
    @Log("学生取消学习内容")
    @DeleteMapping("/student_learning/{id}")
    public ResponseEntity<?> delMyStudy(@MustStudentLogin LoginUser loginUser,
                                        @PathVariable String id) {
        studentLearningService.delMyLearning(loginUser, id);
        return RestModel.noContent();
    }
}
