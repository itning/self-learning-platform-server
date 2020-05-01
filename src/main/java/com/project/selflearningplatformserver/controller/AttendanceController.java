package com.project.selflearningplatformserver.controller;

import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.dto.RestModel;
import com.project.selflearningplatformserver.log.Log;
import com.project.selflearningplatformserver.security.MustAdminLogin;
import com.project.selflearningplatformserver.security.MustLogin;
import com.project.selflearningplatformserver.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * 教师/学生出勤
 *
 * @author itning
 * @date 2020/5/1 22:30
 */
@RestController
public class AttendanceController {
    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    /**
     * 管理员获取所有出勤信息
     *
     * @param loginUser 登录用户
     * @param roleId    要获取的角色ID
     * @param page      分页
     * @param size      每页数量
     * @return ResponseEntity
     */
    @GetMapping("/attendances")
    public ResponseEntity<?> getAllAttendance(@MustAdminLogin LoginUser loginUser,
                                              @RequestParam String roleId,
                                              @RequestParam(defaultValue = "1") int page,
                                              @RequestParam(defaultValue = "20") int size) {
        return RestModel.ok(attendanceService.getAllAttendances(page, size, roleId));
    }

    /**
     * 管理员删除出勤信息
     *
     * @param loginUser 登录用户
     * @param id        出勤信息ID
     * @return ResponseEntity
     */
    @Log("删除出勤")
    @DeleteMapping("/attendance/{id}")
    public ResponseEntity<?> delAttendance(@MustAdminLogin LoginUser loginUser,
                                           @PathVariable String id) {
        attendanceService.delAttendance(id);
        return RestModel.noContent();
    }

    /**
     * 管理员新增出勤信息
     *
     * @param loginUser 登录用户
     * @param userId    新增出勤的用户ID
     * @param date      出勤时间
     * @return ResponseEntity
     */
    @Log("管理员新增出勤")
    @PostMapping("/attendance_admin")
    public ResponseEntity<?> newAttendance(@MustAdminLogin LoginUser loginUser,
                                           @RequestParam String userId,
                                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date) {
        return RestModel.created(attendanceService.newAttendance(userId, date));
    }

    /**
     * 学生/教师新增出勤
     *
     * @param loginUser 登录用户
     * @return ResponseEntity
     */
    @Log("新增出勤")
    @PostMapping("/attendance")
    public ResponseEntity<?> newAttendance(@MustLogin(role = {MustLogin.ROLE.STUDENT, MustLogin.ROLE.TEACHER}) LoginUser loginUser) {
        return RestModel.created(attendanceService.newAttendance(loginUser));
    }

    /**
     * 管理员修改出勤
     *
     * @param loginUser 登录用户
     * @param id        出勤ID
     * @param date      出勤日期
     * @return ResponseEntity
     */
    @Log("修改出勤")
    @PostMapping("/attendance_change")
    public ResponseEntity<?> updateAttendance(@MustAdminLogin LoginUser loginUser,
                                              @RequestParam String id,
                                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date) {
        attendanceService.updateAttendance(id, date);
        return RestModel.noContent();
    }
}
