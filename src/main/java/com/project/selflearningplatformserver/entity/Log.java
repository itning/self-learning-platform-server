package com.project.selflearningplatformserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    private String id;

    private String content;

    private String userId;

    private Date gmtCreate;

    private Date gmtModified;
}