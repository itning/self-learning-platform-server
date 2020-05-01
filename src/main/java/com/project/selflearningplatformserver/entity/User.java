package com.project.selflearningplatformserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;

    private String name;

    private String username;
    @ToString.Exclude
    private String password;

    private Boolean freeze;

    private String salt;

    private String roleId;

    private Date gmtCreate;

    private Date gmtModified;
}