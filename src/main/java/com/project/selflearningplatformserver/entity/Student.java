package com.project.selflearningplatformserver.entity;

import java.util.Date;

public class Student {
    private String userId;

    private String studentClassId;

    private Date gmtCreate;

    private Date gmtModified;

    public Student(String userId, String studentClassId, Date gmtCreate, Date gmtModified) {
        this.userId = userId;
        this.studentClassId = studentClassId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public Student() {
        super();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getStudentClassId() {
        return studentClassId;
    }

    public void setStudentClassId(String studentClassId) {
        this.studentClassId = studentClassId == null ? null : studentClassId.trim();
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