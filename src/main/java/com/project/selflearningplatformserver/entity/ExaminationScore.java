package com.project.selflearningplatformserver.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ExaminationScore {
    private String id;

    private String studentId;

    private String subject;

    private BigDecimal score;

    private String examId;

    private Date gmtCreate;

    private Date gmtModified;

    public ExaminationScore(String id, String studentId, String subject, BigDecimal score, String examId, Date gmtCreate, Date gmtModified) {
        this.id = id;
        this.studentId = studentId;
        this.subject = subject;
        this.score = score;
        this.examId = examId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public ExaminationScore() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId == null ? null : examId.trim();
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