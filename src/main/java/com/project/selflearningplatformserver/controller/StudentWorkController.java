package com.project.selflearningplatformserver.controller;

import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.dto.RestModel;
import com.project.selflearningplatformserver.log.Log;
import com.project.selflearningplatformserver.security.MustLogin;
import com.project.selflearningplatformserver.security.MustStudentLogin;
import com.project.selflearningplatformserver.security.MustTeacherLogin;
import com.project.selflearningplatformserver.service.StudentWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author itning
 * @date 2020/5/2 11:45
 */
@RestController
public class StudentWorkController {
    private final StudentWorkService studentWorkService;

    @Autowired
    public StudentWorkController(StudentWorkService studentWorkService) {
        this.studentWorkService = studentWorkService;
    }

    /**
     * 教师批改作业
     *
     * @param loginUser     登陆用户
     * @param studentWorkId 学生作业ID
     * @param suggest       建议
     * @param score         分数
     * @return ResponseEntity
     */
    @Log("教师批改作业")
    @PostMapping("/student_work_review")
    public ResponseEntity<?> teacherReview(@MustTeacherLogin LoginUser loginUser,
                                           @RequestParam String studentWorkId,
                                           @RequestParam String suggest,
                                           @RequestParam int score) {
        return RestModel.created(studentWorkService.teacherReview(loginUser, studentWorkId, suggest, score));
    }

    /**
     * 教师或学生下载作业
     *
     * @param loginUser 登录用户
     * @param id        作业ID
     * @param response  {@link HttpServletResponse}
     */
    @GetMapping("/download_student_work/{id}")
    public void downloadStudentWork(@MustLogin(role = {MustLogin.ROLE.STUDENT, MustLogin.ROLE.TEACHER}) LoginUser loginUser,
                                    @RequestHeader(name = HttpHeaders.RANGE, required = false) String range,
                                    @PathVariable String id,
                                    HttpServletResponse response) {
        studentWorkService.downloadWorkFile(loginUser, id, response, range);
    }

    /**
     * 学生删除作业
     *
     * @param loginUser 登录用户
     * @param id        作业ID
     * @return ResponseEntity
     */
    @Log("学生删除作业")
    @DeleteMapping("/student_work/{id}")
    public ResponseEntity<?> studentDelWork(@MustStudentLogin LoginUser loginUser,
                                            @PathVariable String id) {
        studentWorkService.delWork(loginUser, id);
        return RestModel.noContent();
    }

    /**
     * 学生上传作业
     *
     * @param loginUser         登录用户
     * @param file              文件
     * @param studentLearningId 学生学习ID
     * @return ResponseEntity
     */
    @Log("学生上传作业")
    @PostMapping("/student_work")
    public ResponseEntity<?> studentUploadWork(@MustStudentLogin LoginUser loginUser,
                                               @RequestParam("file") MultipartFile file,
                                               @RequestParam String studentLearningId) {
        return RestModel.created(studentWorkService.upload(loginUser, studentLearningId, file));
    }

    /**
     * 学生获取自己的作业
     *
     * @param loginUser         登录用户
     * @param learningContentId 学习内容ID
     * @return ResponseEntity
     */
    @GetMapping("/student_work")
    public ResponseEntity<?> getOwnWork(@MustStudentLogin LoginUser loginUser,
                                        @RequestParam String learningContentId) {
        return RestModel.ok(studentWorkService.getOwnWork(loginUser, learningContentId));
    }
}
