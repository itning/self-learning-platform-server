package com.project.selflearningplatformserver.entity;

import java.util.Date;

public class StudentLearning {
    private String id;

    private String learningContentId;

    private String studentId;

    private Date gmtCreate;

    private Date gmtModified;

    public StudentLearning(String id, String learningContentId, String studentId, Date gmtCreate, Date gmtModified) {
        this.id = id;
        this.learningContentId = learningContentId;
        this.studentId = studentId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public StudentLearning() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLearningContentId() {
        return learningContentId;
    }

    public void setLearningContentId(String learningContentId) {
        this.learningContentId = learningContentId == null ? null : learningContentId.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
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