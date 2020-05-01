package com.project.selflearningplatformserver.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author itning
 * @date 2020/5/1 13:34
 */
@Data
public class LoginUser {
    /**
     * 管理员ID
     */
    public static final String ROLE_ADMIN_ID = "a";
    /**
     * 教师ID
     */
    public static final String ROLE_TEACHER_ID = "b";
    /**
     * 学生ID
     */
    public static final String ROLE_STUDENT_ID = "c";

    private String id;

    private String name;

    private String username;

    private String roleId;

    private Date gmtCreate;

    private Date gmtModified;
}
