package com.project.selflearningplatformserver.dto;

import java.util.Date;

/**
 * @author itning
 * @date 2020/5/1 13:34
 */
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
