package com.project.selflearningplatformserver.controller;

import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.dto.RestModel;
import com.project.selflearningplatformserver.entity.User;
import com.project.selflearningplatformserver.log.Log;
import com.project.selflearningplatformserver.security.MustAdminLogin;
import com.project.selflearningplatformserver.security.MustLogin;
import com.project.selflearningplatformserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author itning
 * @date 2020/5/1 14:27
 */
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 管理员获取所有教师信息
     *
     * @param loginUser 登录用户
     * @param page      页码
     * @param size      每页数量
     * @return ResponseEntity
     */
    @GetMapping("/teachers")
    public ResponseEntity<?> getAllTeachers(@MustAdminLogin LoginUser loginUser,
                                            @RequestParam(defaultValue = "1") int page,
                                            @RequestParam(defaultValue = "20") int size) {
        return RestModel.ok(userService.getAllTeacherUser(page, size));
    }


    /**
     * 管理员获取所有学生信息
     *
     * @param loginUser 登录用户
     * @param page      页码
     * @param size      每页数量
     * @return ResponseEntity
     */
    @GetMapping("/students")
    public ResponseEntity<?> getAllStudents(@MustAdminLogin LoginUser loginUser,
                                            @RequestParam(defaultValue = "1") int page,
                                            @RequestParam(defaultValue = "20") int size) {
        return RestModel.ok(userService.getAllStudentUser(page, size));
    }

    /**
     * 管理员新增教师
     *
     * @param loginUser 登录用户
     * @param name      新增的教师名
     * @param username  新增的教师用户名
     * @return ResponseEntity
     */
    @PostMapping("/teacher")
    public ResponseEntity<?> newTeacher(@MustAdminLogin LoginUser loginUser,
                                        @RequestParam String name,
                                        @RequestParam String username) {
        return RestModel.created(userService.newTeacher(name, username));
    }

    /**
     * <p>用户修改信息（修改姓名，密码）
     * <p>管理员冻结账户，修改用户信息
     *
     * @param loginUser 登录用户
     * @param user      修改载体
     * @return ResponseEntity
     */
    @Log("更新用户信息")
    @PatchMapping("/user")
    public ResponseEntity<?> updateUserInfo(@MustLogin(role = {MustLogin.ROLE.ADMIN, MustLogin.ROLE.TEACHER, MustLogin.ROLE.STUDENT}) LoginUser loginUser,
                                            @RequestBody User user) {
        userService.updateUserInfo(loginUser, user);
        return RestModel.noContent();
    }

    /**
     * <p>管理员删除账户
     * <p>无法删除自己，即管理员无法删除管理员
     *
     * @param loginUser 登录用户
     * @param userId    要删除的用户ID
     * @return ResponseEntity
     */
    @Log("删除用户信息")
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> delUserInfo(@MustAdminLogin LoginUser loginUser,
                                         @PathVariable String userId) {
        userService.delUserInfo(userId);
        return RestModel.noContent();
    }
}
