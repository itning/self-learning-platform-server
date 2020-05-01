package com.project.selflearningplatformserver.entity;

import java.util.Date;

public class LearningContent {
    private String id;

    private String subjectId;

    private String contentUri;

    private Date gmtCreate;

    private Date gmtModified;

    public LearningContent(String id, String subjectId, String contentUri, Date gmtCreate, Date gmtModified) {
        this.id = id;
        this.subjectId = subjectId;
        this.contentUri = contentUri;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public LearningContent() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    public String getContentUri() {
        return contentUri;
    }

    public void setContentUri(String contentUri) {
        this.contentUri = contentUri == null ? null : contentUri.trim();
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