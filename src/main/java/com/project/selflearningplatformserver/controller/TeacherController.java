package com.project.selflearningplatformserver.controller;

import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.dto.RestModel;
import com.project.selflearningplatformserver.entity.Teacher;
import com.project.selflearningplatformserver.log.Log;
import com.project.selflearningplatformserver.security.MustTeacherLogin;
import com.project.selflearningplatformserver.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 新增/修改教师信息
 *
 * @author itning
 * @date 2020/5/6 5:53
 */
@RestController
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    /**
     * 获取教师所有信息
     *
     * @param loginUser 登录用户
     * @return ResponseEntity
     */
    @GetMapping("/teacher_infos")
    public ResponseEntity<?> getAllTeacherInfo(@MustTeacherLogin LoginUser loginUser) {
        return RestModel.ok(teacherService.getAllTeacherInfo(loginUser));
    }

    /**
     * 教师删除信息
     *
     * @param loginUser 登录用户
     * @param id        信息ID
     * @return ResponseEntity
     */
    @Log("教师删除信息")
    @DeleteMapping("/teacher_info/{id}")
    public ResponseEntity<?> delTeacherInfo(@MustTeacherLogin LoginUser loginUser,
                                            @PathVariable String id) {
        teacherService.delTeacherInfo(loginUser, id);
        return RestModel.noContent();
    }

    /**
     * 教师新增信息
     *
     * @param loginUser      登录用户
     * @param attributeKey   属性KEY
     * @param attributeValue 属性值
     * @return ResponseEntity
     */
    @Log("教师新增信息")
    @PostMapping("/teacher_info")
    public ResponseEntity<?> newTeacherInfo(@MustTeacherLogin LoginUser loginUser,
                                            @RequestParam String attributeKey,
                                            @RequestParam String attributeValue) {
        return RestModel.created(teacherService.newTeacherInfo(loginUser, attributeKey, attributeValue));
    }

    /**
     * 教师修改信息
     *
     * @param loginUser 登录用户
     * @param teacher   修改的教师信息
     * @return ResponseEntity
     */
    @Log("教师修改信息")
    @PatchMapping("/teacher_info")
    public ResponseEntity<?> updateTeacherInfo(@MustTeacherLogin LoginUser loginUser,
                                               @RequestBody Teacher teacher) {
        teacherService.updateTeacherInfo(loginUser, teacher);
        return RestModel.noContent();
    }
}
