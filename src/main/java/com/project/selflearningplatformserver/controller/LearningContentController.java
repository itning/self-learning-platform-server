package com.project.selflearningplatformserver.controller;

import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.dto.RestModel;
import com.project.selflearningplatformserver.entity.LearningContent;
import com.project.selflearningplatformserver.log.Log;
import com.project.selflearningplatformserver.security.MustLogin;
import com.project.selflearningplatformserver.security.MustStudentLogin;
import com.project.selflearningplatformserver.security.MustTeacherLogin;
import com.project.selflearningplatformserver.service.LearningContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

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
     * @return ResponseEntity
     */
    @GetMapping("/learning_contents")
    public ResponseEntity<?> allLearningContent(@MustLogin LoginUser loginUser,
                                                @RequestParam String subjectId) {
        return RestModel.ok(learningContentService.getAllBySubjectId(subjectId));
    }

    /**
     * 新增学习内容
     *
     * @param loginUser 登录用户
     * @param file      文件
     * @param aidFile   其他文件
     * @param name      名称
     * @param subjectId 科目ID
     * @return ResponseEntity
     */
    @Log("新增学习内容")
    @PostMapping("/learning_content")
    public ResponseEntity<?> uploadLearningContent(@MustTeacherLogin LoginUser loginUser,
                                                   @RequestParam("file") MultipartFile file,
                                                   @RequestParam("aid") MultipartFile aidFile,
                                                   @RequestParam String name,
                                                   @RequestParam String subjectId) {
        return RestModel.created(learningContentService.newLearningContent(loginUser, file, aidFile, subjectId, name));
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

    /**
     * 教师修改学习内容
     *
     * @param loginUser       登录用户
     * @param learningContent 学习内容
     * @return ResponseEntity
     */
    @Log("修改学习内容")
    @PatchMapping("/learning_content")
    public ResponseEntity<?> updateLearningContent(@MustTeacherLogin LoginUser loginUser,
                                                   @RequestBody LearningContent learningContent) {
        learningContentService.updateLearningContent(loginUser, learningContent.getId(), learningContent.getName());
        return RestModel.noContent();
    }

    /**
     * 学生获取所有可以学习的内容（排除已经选择的）
     *
     * @return ResponseEntity
     */
    @GetMapping("/learning_content_of_student")
    public ResponseEntity<?> getAllCanLearningContent(@MustStudentLogin LoginUser loginUser) {
        return RestModel.ok(learningContentService.getAllCanLearningContent(loginUser));
    }

    /**
     * 下载学习内容文件
     *
     * @param loginUser         登录用户
     * @param type              哪种文件(video/aid)
     * @param learningContentId 学习内容ID
     * @param range             {@link HttpHeaders#RANGE}
     * @param response          {@link HttpServletResponse}
     */
    @GetMapping("/download_learning_content/{type}/{learningContentId}")
    public void downloadLearningContentAid(@MustLogin(role = {MustLogin.ROLE.STUDENT, MustLogin.ROLE.TEACHER}) LoginUser loginUser,
                                           @PathVariable String type,
                                           @PathVariable String learningContentId,
                                           @RequestHeader(name = HttpHeaders.RANGE, required = false) String range,
                                           HttpServletResponse response) {
        learningContentService.downloadLearningContent(loginUser, learningContentId, range, response, type);
    }
}
