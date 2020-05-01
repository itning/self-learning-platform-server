package com.project.selflearningplatformserver.controller;

import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.dto.RestModel;
import com.project.selflearningplatformserver.entity.StudentClass;
import com.project.selflearningplatformserver.log.Log;
import com.project.selflearningplatformserver.security.MustTeacherLogin;
import com.project.selflearningplatformserver.service.StudentClassServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author itning
 * @date 2020/5/1 20:21
 */
@RestController
public class StudentClassController {
    private final StudentClassServer studentClassServer;

    @Autowired
    public StudentClassController(StudentClassServer studentClassServer) {
        this.studentClassServer = studentClassServer;
    }

    /**
     * 教师获取自己的班级
     *
     * @param loginUser 登录用户
     * @return ResponseEntity
     */
    @GetMapping("/student_classes")
    public ResponseEntity<?> getAllStudentClass(@MustTeacherLogin LoginUser loginUser) {
        return RestModel.ok(studentClassServer.getAll(loginUser));
    }

    /**
     * 教师新增班级
     *
     * @param loginUser 登录用户
     * @param name      班级名
     * @return ResponseEntity
     */
    @Log("新增班级")
    @PostMapping("/student_class")
    public ResponseEntity<?> newStudentClass(@MustTeacherLogin LoginUser loginUser,
                                             @RequestParam String name) {
        return RestModel.created(studentClassServer.newStudentClass(loginUser, name));
    }

    /**
     * 教师修改班级
     *
     * @param loginUser    登录用户
     * @param studentClass 班级信息
     * @return ResponseEntity
     */
    @Log("修改班级")
    @PatchMapping("/student_class")
    public ResponseEntity<?> updateStudentClass(@MustTeacherLogin LoginUser loginUser,
                                                @RequestBody StudentClass studentClass) {
        studentClassServer.updateStudentClass(loginUser, studentClass);
        return RestModel.noContent();
    }

    /**
     * 教师删除班级
     *
     * @param loginUser      登录用户
     * @param studentClassId 班级ID
     * @return ResponseEntity
     */
    @Log("删除班级")
    @DeleteMapping("/student_class/{studentClassId}")
    public ResponseEntity<?> delStudentClass(@MustTeacherLogin LoginUser loginUser,
                                             @PathVariable String studentClassId) {
        studentClassServer.del(loginUser, studentClassId);
        return RestModel.noContent();
    }
}
