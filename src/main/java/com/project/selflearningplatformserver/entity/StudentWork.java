package com.project.selflearningplatformserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentWork {
    private String id;

    private String fileUri;

    private Integer score;

    private String suggest;

    private Date gmtCreate;

    private Date gmtModified;
}