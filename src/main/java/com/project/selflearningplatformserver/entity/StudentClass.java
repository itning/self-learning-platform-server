package com.project.selflearningplatformserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentClass {
    private String id;

    private String name;

    private String userId;

    private Date gmtCreate;

    private Date gmtModified;
}