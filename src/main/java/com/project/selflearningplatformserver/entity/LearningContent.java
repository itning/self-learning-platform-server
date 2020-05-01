package com.project.selflearningplatformserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LearningContent {
    private String id;

    private String subjectId;

    private String contentUri;

    private Date gmtCreate;

    private Date gmtModified;
}