package com.project.selflearningplatformserver.entity;

import java.util.Date;

public class StudentWork {
    private String id;

    private String learningContentId;

    private String fileUri;

    private Integer score;

    private String suggest;

    private Date gmtCreate;

    private Date gmtModified;

    public StudentWork(String id, String learningContentId, String fileUri, Integer score, String suggest, Date gmtCreate, Date gmtModified) {
        this.id = id;
        this.learningContentId = learningContentId;
        this.fileUri = fileUri;
        this.score = score;
        this.suggest = suggest;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public StudentWork() {
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

    public String getFileUri() {
        return fileUri;
    }

    public void setFileUri(String fileUri) {
        this.fileUri = fileUri == null ? null : fileUri.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest == null ? null : suggest.trim();
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