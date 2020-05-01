package com.project.selflearningplatformserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentLearning {
    private String id;

    private String learningContentId;

    private String studentId;

    private Date gmtCreate;

    private Date gmtModified;
}