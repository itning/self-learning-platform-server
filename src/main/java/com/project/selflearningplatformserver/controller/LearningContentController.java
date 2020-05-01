package com.project.selflearningplatformserver.controller;

import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.dto.RestModel;
import com.project.selflearningplatformserver.log.Log;
import com.project.selflearningplatformserver.security.MustLogin;
import com.project.selflearningplatformserver.security.MustTeacherLogin;
import com.project.selflearningplatformserver.service.LearningContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author itning
 * @date 2020/5/1 20:53
 */
@RestController
public class LearningContentController {
    private final LearningContentService learningContentService;

    @Autowired
    public LearningContentController(LearningContentService learningContentService) {
        this.learningContentService = learningContentService;
    }

    /**
     * 根据科目获取学习内容
     *
     * @param loginUser 登录用户
     * @param subjectId 科目ID
     * @param page      分页
     * @param size      每页数量
     * @return ResponseEntity
     */
    @GetMapping("/learning_contents")
    public ResponseEntity<?> allLearningContent(@MustLogin LoginUser loginUser,
                                                @RequestParam String subjectId,
                                                @RequestParam(defaultValue = "1") int page,
                                                @RequestParam(defaultValue = "20") int size) {
        return RestModel.ok(learningContentService.getAllBySubjectId(subjectId, page, size));
    }

    /**
     * 教师删除学习内容
     *
     * @param loginUser 登录用户
     * @param id        学习内容ID
     * @return ResponseEntity
     */
    @Log("删除学习内容")
    @DeleteMapping("/learning_content/{id}")
    public ResponseEntity<?> delLearningContent(@MustTeacherLogin LoginUser loginUser,
                                                @PathVariable String id) {
        learningContentService.delLearningContent(loginUser, id);
        return RestModel.noContent();
    }
}
