package com.project.selflearningplatformserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Examination {
    private String id;

    private String userId;

    private String name;

    private Date gmtCreate;

    private Date gmtModified;
}