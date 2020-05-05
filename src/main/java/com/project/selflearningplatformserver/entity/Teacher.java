package com.project.selflearningplatformserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private String id;

    private String userId;

    private String attributeKey;

    private String attributeValue;

    private Date gmtCreate;

    private Date gmtModified;
}